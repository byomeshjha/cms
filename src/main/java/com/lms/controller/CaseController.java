package com.lms.controller;

import java.beans.PropertyEditorSupport;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lms.common.bean.GridJson;
import com.lms.common.entity.CaseDocket;
import com.lms.common.entity.CaseDocument;
import com.lms.common.entity.CaseNote;
import com.lms.common.entity.CaseStatus;
import com.lms.common.entity.CaseType;
import com.lms.common.entity.Firm;
import com.lms.ejb.CaseEjb;
import com.lms.util.JSONUtil;
import com.lms.util.SessionUtils;


@Controller
public class CaseController extends BaseController {
	
	private Logger logger = Logger.getLogger(CaseController.class.getName());
	
	@EJB(mappedName = "java:app/lms/CaseEjb")
	private CaseEjb caseEjb;
	
	private DateFormat dateFormatter = new SimpleDateFormat("mm/dd/yyyy");
	private static Map<Integer, String> defaultColMap = new HashMap<>();
	private static Map<String, String> documentTypeMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
		defaultColMap.put(0, "Case_Docket_ID");
		defaultColMap.put(1, "case_name");
		defaultColMap.put(5, "date_time_created");
		
		documentTypeMap.put("TXT", "Text/Plain");
		documentTypeMap.put("DOCX", "Microsoft/Word");
		documentTypeMap.put("DOC", "Microsoft/Word");
		documentTypeMap.put("PDF", "Adobe/Pdf");
		documentTypeMap.put("XLS", "Microsoft/Excel");
		documentTypeMap.put("XLSX", "Microsoft/Excel");
		documentTypeMap.put("JPG", "Image/JPEG");
		documentTypeMap.put("JPEG", "Image/JPEG");
		documentTypeMap.put("GIF", "Image/GIF");
		documentTypeMap.put("TIFF", "Image/TIFF");
	}
	
	
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
		    @Override
		    public void setAsText(String text) {
		    	try {
		    		if(text != null && !"".equals(text)) {
		    			Date d = dateFormatter.parse(text);
		    			setValue(new Timestamp(d.getTime()));
		    		}
		    	}
		    	catch(ParseException e) {
		    		e.printStackTrace();
		    	}
		    }
		});
		binder.registerCustomEditor(List.class, "caseNotes", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element != null) {
                	CaseNote o = (CaseNote) element;
                    return o;
                }
                return null;
            }
        });
		binder.registerCustomEditor(List.class, "documents", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element != null) {
                	CaseDocument o = (CaseDocument) element;
                    return o;
                }
                return null;
            }
        });
	}
	

	@RequestMapping(value = "registerCase", method = { RequestMethod.POST })
	public ModelAndView registerCase(@ModelAttribute("caseRegistrationForm") CaseDocket o, 
			@RequestParam("file1") MultipartFile[] files,
			ModelMap model, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("case registration called: " + o.toString());
		//logger.info("case registration sessionUser: " + sessionUser);
		

		List<String> msgs = validateCaseRegistration(o);
		if (msgs.size() > 0) {
			model.put("error", msgs);
			try {
				CaseDocket cd = initializeFormObject(CaseDocket.class, o, request);
				model.addAttribute("caseRegistrationForm", cd);
				model.addAttribute("NAV_SELECTED", "case/register");
			}
			catch(IllegalAccessException|InstantiationException e) {
				logger.severe(e.getLocalizedMessage());
				e.printStackTrace();
			}
			return handleError(request, msgs, "registerCase");
		}
		
		try {
			Firm firm = SessionUtils.getFirmForLoggedinUser(request);
			o.setFirmId(firm.getFirmId());
			o.setCaseManagerId(o.getCaseManager().getUserId());
			o.setClientId(o.getClient().getClientId());
			
			o.setLastModifiedByUser(SessionUtils.getSessionUser(request));
			
			String firmDocumentPath = firm.getDocumentPath();
			for (MultipartFile file : files) {
				String documentName = file.getOriginalFilename();
				String documentType = file.getContentType();
				long documentSize = file.getSize();
				if(documentSize <= 0) continue;
				CaseDocument cd = new CaseDocument();
				cd.setCaseDocket(o);
				cd.setCaseDocketId(o.getCaseDocketId());
				cd.setDocumentName(documentName);
				cd.setDocumentType(documentType);
				cd.setDocumentPath(firmDocumentPath);
				cd.setDocumentSize(documentSize);
				if(o.getDocuments() == null) {
					o.setDocuments(new ArrayList<>());
				}
				o.getDocuments().add(cd);
			}
			o = caseEjb.saveCase(o);
			processMultipartFiles(files, firmDocumentPath);
		}
		catch(Exception e) {
			msgs.add(e.getMessage());
			model.addAttribute("NAV_SELECTED", "case/register");
			return handleError(request, msgs, "registerCase");
		}
		
		String formattedMessage = String.format("Case %1s Successfully Saved", o.getCaseName());
		msgs.add(formattedMessage);
		
		model.addAttribute("NAV_SELECTED", "case/list");
		
		return handleSuccess(request, msgs, "caseList");
	}
	
	
	
	@RequestMapping(value = "updateCase/{caseId}", method = { RequestMethod.POST })
	public String updateCase(@PathVariable("caseId") Integer caseId,
			ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("case update called: " + caseId);
		
		CaseDocket o = caseEjb.getCase(caseId);
		List<CaseDocument> documents = caseEjb.getCaseDocuments(caseId);
		for(CaseDocument d : documents) {
			String name = d.getDocumentName();
			String suffix = name.substring(name.lastIndexOf(".") + 1);
			String mappedType = documentTypeMap.get(suffix.toUpperCase());
			if(mappedType != null) {
				d.setMappedType(mappedType);
			}
		}
		o.setDocuments(documents);
		List<String> msgs = new ArrayList<>();
		try {
			o = initializeFormObject(CaseDocket.class, o, request);
			model.addAttribute("caseRegistrationForm", o);
		}
		catch(IllegalAccessException|InstantiationException e) {
			msgs.add(e.getLocalizedMessage());
			model.put("error", msgs);
			logger.severe(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		model.addAttribute("NAV_SELECTED", "case/register");
		
		return "registerCase";
		
	}
	
	@RequestMapping(value = "firm/case/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String clientList(HttpServletRequest request) throws IOException {
		
		//Fetch Page display length
    	Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		Integer pageNumber = 0;
    	if (null != request.getParameter("iDisplayStart"))
    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/pageDisplayLength)+1;		
    	
    	//Fetch search parameter
    	String searchParameter = request.getParameter("sSearch");
    	
    	int offset = 0;
    	if(pageNumber > 1) {
    		offset = (((pageNumber * pageDisplayLength) - pageDisplayLength));
    	}
    	
    	int firstRow = (pageNumber == 1 ? 0 : offset);
    	String sortOrder = request.getParameter("sSortDir_0");
    	Integer sortCol = Integer.valueOf(request.getParameter("iSortCol_0"));
    	String colName = defaultColMap.get(sortCol);
    	
    	//Create page list data
    	List<CaseDocket> caseList = createPaginationData(firstRow, pageDisplayLength, colName, sortOrder);
    	if(caseList == null || caseList.size() <= 0) {
    		CaseDocket cd = new CaseDocket();
    		cd.setCaseStatus(new CaseStatus());
    		cd.setCaseType(new CaseType());
    		caseList = new ArrayList<>();
    		caseList.add(cd);
    	}
		
		//Search functionality: Returns filtered list based on search parameter
    	caseList = getListBasedOnSearchParameter(searchParameter, caseList);
		int total = caseEjb.getCaseCount();
		
		GridJson<CaseDocket> clientJsonObject = new GridJson<>();
		//Set Total display record
		clientJsonObject.setiTotalDisplayRecords(total);
		//Set Total record
		clientJsonObject.setiTotalRecords(total);
		clientJsonObject.setAaData(caseList);
		String json2 = "";
		try {
			@SuppressWarnings("rawtypes")
			JSONUtil<GridJson> jsonUtil = new JSONUtil<>(GridJson.class);
			json2 = jsonUtil.createJsonFromObject(clientJsonObject);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("JSON: " + json2);
	
		return json2;
	}
	
	/////////////////////////////////////
	@RequestMapping(value = "case/document/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String caseDocumentList(HttpServletRequest request) throws IOException {
		
		//Fetch Page display length
    	Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		Integer pageNumber = 0;
    	if (null != request.getParameter("iDisplayStart"))
    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/pageDisplayLength)+1;		
    	
    	//Fetch search parameter
    	String searchParameter = request.getParameter("sSearch");
    	
    	int offset = 0;
    	if(pageNumber > 1) {
    		offset = (((pageNumber * pageDisplayLength) - pageDisplayLength));
    	}
    	
    	int firstRow = (pageNumber == 1 ? 0 : offset);
    	String sortOrder = request.getParameter("sSortDir_0");
    	Integer sortCol = Integer.valueOf(request.getParameter("iSortCol_0"));
    	String colName = defaultColMap.get(sortCol);
    	
    	//Create page list data
    	List<CaseDocket> caseList = createPaginationData(firstRow, pageDisplayLength, colName, sortOrder);
    	if(caseList == null || caseList.size() <= 0) {
    		CaseDocket cd = new CaseDocket();
    		cd.setCaseStatus(new CaseStatus());
    		cd.setCaseType(new CaseType());
    		caseList = new ArrayList<>();
    		caseList.add(cd);
    	}
		
		//Search functionality: Returns filtered list based on search parameter
    	caseList = getListBasedOnSearchParameter(searchParameter, caseList);
		int total = caseEjb.getCaseCount();
		
		GridJson<CaseDocket> clientJsonObject = new GridJson<>();
		//Set Total display record
		clientJsonObject.setiTotalDisplayRecords(total);
		//Set Total record
		clientJsonObject.setiTotalRecords(total);
		clientJsonObject.setAaData(caseList);
		String json2 = "";
		try {
			@SuppressWarnings("rawtypes")
			JSONUtil<GridJson> jsonUtil = new JSONUtil<>(GridJson.class);
			json2 = jsonUtil.createJsonFromObject(clientJsonObject);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("JSON: " + json2);
	
		return json2;
	}
	//////////////////
	
	private List<CaseDocket> getListBasedOnSearchParameter(
			String searchParameter, List<CaseDocket> caseList) {
		if (null != searchParameter && !searchParameter.equals("")) {
			List<CaseDocket> caseListForSearch = new ArrayList<>();
			searchParameter = searchParameter.toUpperCase();
			for (CaseDocket cased : caseList) {
				if (cased.getCaseName().toUpperCase().indexOf(searchParameter)!= -1) {
					caseListForSearch.add(cased);
				}
				
			}
			caseList = caseListForSearch;
			caseListForSearch = null;
		}
		return caseList;
	}
 
	private List<CaseDocket> createPaginationData(int firstRow, Integer maxRow, String sortCol, String sortOrder) {
		List<CaseDocket> caseList = caseEjb.getCaseList(firstRow, maxRow, sortCol, sortOrder);

		return caseList;
	}
	
	private List<String> validateCaseRegistration(CaseDocket o) {
		List<String> validationMessages = new ArrayList<>();
		if (null != o) {
			if (o.getCaseName() == null || "".equals(o.getCaseName().trim())) {
				validationMessages.add("Please enter valid case name.");
			}
		}

		return validationMessages;
	}
	
	private String processMultipartFiles(MultipartFile[] files, String firmDocumentPath) {
		String fileName = null;
    	String msg = "";
    	if (files != null && files.length > 0) {
    		for(int i = 0 ;i < files.length; i++){
	            try {
	                fileName = files[i].getOriginalFilename();
	                byte[] bytes = files[i].getBytes();
	                BufferedOutputStream buffStream = 
	                        new BufferedOutputStream(
	                        		new FileOutputStream(
	                        				new File(firmDocumentPath + fileName)));
	                buffStream.write(bytes);
	                buffStream.close();
	                msg += "You have successfully uploaded " + fileName +"<br/>";
	            } catch (Exception e) {
	                msg += "You failed to upload " + fileName + ": " + e.getMessage();
	            }
    		}
    		return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
	}

}
