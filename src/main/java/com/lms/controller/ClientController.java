package com.lms.controller;

import java.beans.PropertyEditorSupport;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lms.common.bean.GridJson;
import com.lms.common.entity.Address;
import com.lms.common.entity.Biographic;
import com.lms.common.entity.Client;
import com.lms.common.entity.Member;
import com.lms.ejb.ClientEjb;
import com.lms.util.JSONUtil;

@Controller
public class ClientController extends BaseController {
	
	private Logger logger = Logger.getLogger(ClientController.class.getName());
	
	
	@EJB(mappedName = "java:app/lms/ClientEjb")
	private ClientEjb clientEjb;
	
	private DateFormat dateFormatter = new SimpleDateFormat("mm/dd/yyyy");
	private static Map<Integer, String> defaultColMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
		defaultColMap.put(0, "Client_ID");
		defaultColMap.put(1, "first_name");
		defaultColMap.put(2, "last_name");
		defaultColMap.put(3, "primary_phone");
		defaultColMap.put(4, "email_address");
		defaultColMap.put(5, "date_time_created");
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
		binder.registerCustomEditor(List.class, "members", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element != null) {
                	Biographic o = (Biographic) element;
                    return o;
                }
                return null;
            }
        });
		binder.registerCustomEditor(List.class, "addresses", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element != null) {
                	Address o = (Address) element;
                    return o;
                }
                return null;
            }
        });
	}
	
	@RequestMapping(value = {"registerClient", "addMember"}, method = { RequestMethod.POST })
	public ModelAndView registerClient(@ModelAttribute("clientRegistrationForm") Client client
			, ModelMap model, BindingResult result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		String sp = request.getServletPath();
		logger.info("Servlet Path: " + sp);
		logger.info("client registration client: " + client.toString());
		String selectedTab = request.getParameter("selectedTab");

		List<String> msgs = validateClientRegistration(client);
		if (msgs.size() > 0) {
			model.put("error", msgs);
			
			return handleError(request, msgs, "registerClient");
		}
		
		try {
			client = clientEjb.saveClient(client);
		}
		catch(Exception e) {
			msgs.add(e.getMessage());
			return handleError(request, msgs, "registerClient");
		}
		
		// This is necessary for lazy initializers
		client = clientEjb.getClientWithID(client.getClientId());
        model.addAttribute("clientRegistrationForm", client);
		model.addAttribute("TAB_SELECTED", selectedTab);
		model.addAttribute("NAV_SELECTED", "client/register");
		
		if(sp.contains("addMember")) {
			Member m = new Member();
			m.setClientId(client.getClientId());
			model.addAttribute("memberRegistrationForm", m);
			return handleSuccess(request, msgs, "registerMember");
		}
		else {
			return handleSuccess(request, msgs, "registerClient");
		}
	}
	
	
	@RequestMapping(value = "registerMember", method = { RequestMethod.POST })
	public ModelAndView registerMember(@ModelAttribute("memberRegistrationForm") Member member
			, ModelMap model, BindingResult result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException {

		logger.info("member registration: " + member.toString());
		String selectedTab = request.getParameter("selectedTab");

		List<String> msgs = validateMemberRegistration(member);
		if (msgs.size() > 0) {
			model.put("error", msgs);
			model.addAttribute("memberRegistrationForm", member);
			return handleError(request, msgs, "registerMember");
		}
		
		try {
			member = clientEjb.saveMember(member);
		}
		catch(Exception e) {
			msgs.add(e.getMessage());
			model.addAttribute("memberRegistrationForm", member);
			return handleError(request, msgs, "registerMember");
		}
		
		Client client = clientEjb.getClientWithID(member.getClientId());
		
        model.addAttribute("clientRegistrationForm", client);
		model.addAttribute("TAB_SELECTED", selectedTab);
		model.addAttribute("NAV_SELECTED", "client/register");
		
		return handleSuccess(request, msgs, "registerClient");
	}
	
	@RequestMapping(value = "updateClient/{clientId}", method = { RequestMethod.POST })
	public String updateClient(@PathVariable("clientId") Integer clientId,
			ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("client update called: " + clientId);
		
		Client client = clientEjb.getClientWithID(clientId);
		model.addAttribute("clientRegistrationForm", client);
		
		model.addAttribute("NAV_SELECTED", "client/register");
		
		return "registerClient";
	}
	
	@RequestMapping(value = "updateMember/{memberId}", method = { RequestMethod.POST })
	public String updateMember(@PathVariable("memberId") Integer memberId,
			ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("member update called: " + memberId);
		
		Member member = clientEjb.getMemberWithID(memberId);
		model.addAttribute("memberRegistrationForm", member);
		
		model.addAttribute("NAV_SELECTED", "client/register");
		
		return "registerMember";
	}
	
	@RequestMapping(value = "/client/list", method = RequestMethod.GET, produces = "application/json")
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
    	List<Client> clientList = createPaginationData(firstRow, pageDisplayLength, colName, sortOrder, searchParameter);
		
		//Search functionality: Returns filtered list based on search parameter
		//clientList = getListBasedOnSearchParameter(searchParameter, clientList);
		int total = clientEjb.getClientCount();
		
		GridJson<Client> clientJsonObject = new GridJson<>();
		//Set Total display record
		clientJsonObject.setiTotalDisplayRecords(total);
		//Set Total record
		clientJsonObject.setiTotalRecords(total);
		clientJsonObject.setAaData(clientList);
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
	
	/*
	private List<Client> getListBasedOnSearchParameter(
			String searchParameter, List<Client> clientList) {
		if (null != searchParameter && !searchParameter.equals("")) {
			List<Client> clientListForSearch = new ArrayList<>();
			searchParameter = searchParameter.toUpperCase();
			for (Client c : clientList) {
				if (c.getFirstName().toUpperCase().indexOf(searchParameter)!= -1 || c.getLastName().toUpperCase().indexOf(searchParameter)!= -1
						|| c.getPhone1().toUpperCase().indexOf(searchParameter)!= -1 || c.getEmailAddress().toUpperCase().indexOf(searchParameter)!= -1
						) {
					clientListForSearch.add(c);
				}
				
			}
			clientList = clientListForSearch;
			clientListForSearch = null;
		}
		return clientList;
	}
 
	private List<Client> createPaginationData(int firstRow, Integer maxRow, String sortCol, String sortOrder) {
		List<Client> clientList = clientEjb.getClientList(firstRow, maxRow, sortCol, sortOrder);

		return clientList;
	}
	*/
	
	private List<Client> createPaginationData(int firstRow, Integer maxRow, String sortCol, String sortOrder, String searchParameter) {
		List<Client> clientList = null;
		if(searchParameter != null && !"".equals(searchParameter))
		{
			clientList = clientEjb.getClientList(searchParameter);
		}
		else {
			clientList = clientEjb.getClientList(firstRow, maxRow, sortCol, sortOrder);
		}

		return clientList;
	}
	
	private List<String> validateClientRegistration(Client o) {
		List<String> validationMessages = new ArrayList<>();
		if (null != o) {
			if (o.getFirstName() == null) {
				validationMessages.add("Please enter valid first name.");
			}
			if (o.getLastName() == null) {
				validationMessages.add("Please enter valid last name.");
			}
		}

		return validationMessages;
	}
	
	private List<String> validateMemberRegistration(Member o) {
		List<String> validationMessages = new ArrayList<>();
		if (null != o) {
			if (o.getFirstName() == null) {
				validationMessages.add("Please enter valid first name.");
			}
			if (o.getLastName() == null) {
				validationMessages.add("Please enter valid last name.");
			}
		}

		return validationMessages;
	}

}
