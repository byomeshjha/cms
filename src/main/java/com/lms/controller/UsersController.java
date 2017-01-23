package com.lms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lms.common.bean.DataGridBean;
import com.lms.common.bean.GridJson;
import com.lms.common.bean.SessionUserInfo;
import com.lms.common.entity.AppUser;
import com.lms.common.entity.Firm;
import com.lms.common.entity.FirmUser;
import com.lms.common.entity.UserRole;
import com.lms.ejb.ConfigEjb;
import com.lms.util.JSONUtil;

import net.sf.jasperreports.engine.JRException;

@Controller
public class UsersController extends BaseController {

	private Logger logger = Logger.getLogger(UsersController.class.getName());
	
	@Inject @Named
	SessionUserInfo sessionUser;

	@EJB(mappedName = "java:app/lms/ConfigEjb")
	private ConfigEjb configEjb;
	private static Map<Integer, String> defaultColMap = new HashMap<>();
	
	@PostConstruct
	public void init() {
		defaultColMap.put(0, "User_ID");
		defaultColMap.put(1, "first_name");
		defaultColMap.put(2, "last_name");
		defaultColMap.put(3, "Cell_Phone");
		defaultColMap.put(4, "email_address");
		defaultColMap.put(5, "date_time_created");
	}

	@RequestMapping(value = "userlist", method = { RequestMethod.GET })
	public @ResponseBody DataGridBean orgList(HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {

		logger.info("userlist called");

		DataGridBean dgb = new DataGridBean();
		List<AppUser> orgList = configEjb.getAppUserList();
		dgb.setTotal(orgList.size());
		dgb.setRows(orgList);

		return dgb;
	}

	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public ModelAndView login(@ModelAttribute("userForm") FirmUser o, ModelMap model, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("login called user: " + o.getEmailAddress() + ", pass: " + o.getPassword());

		List<String> msgs = validateLoginPost(o);
		if (msgs.size() > 0) {
			model.put("error", msgs);
			return handleError(request, msgs, "login");
		}
		
		Firm firm = getFirmForUser(o);
		o = configEjb.getFirmUser(o, firm);
		String message = "Login failed";
		if (o != null && o.getId() > 0) {
			HttpSession session = (HttpSession) request.getSession(true);
			session.setAttribute("LOGGEDIN_USER", o);
			sessionUser.setSessionUser(o);
			//message = "Login successful";
			//List<String> loginSuccess = new ArrayList<>(1);
			//loginSuccess.add(message);
			//model.put("success", loginSuccess);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:home");
			
			return mav;
		} else {
			List<String> loginFailure = new ArrayList<>(1);
			loginFailure.add(message);
			return handleError(request, loginFailure, "login");
		}
	}

	@RequestMapping(value = "logout", method = { RequestMethod.GET })
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException, ServletException {

		logger.info("logout called");
		String ctx = request.getContextPath();
		HttpSession session = (HttpSession) request.getSession();
		if (session != null)
			session.invalidate();
		response.setHeader("Cache-Control", "no-cache");// HTTP 1.1
		response.setHeader("Pragma", "no-cache");// HTTP 1.0
		response.setDateHeader("Expires", 0);// prevents caching at the proxy
												// server
		response.sendRedirect(ctx + "/login");
	}
	
	@RequestMapping(value = "userroles", method = { RequestMethod.GET })
	public @ResponseBody List<Map<String, String>> userRoles(HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {

		logger.info("userroles called");

		List<UserRole> roles = Arrays.asList(UserRole.values());
		List<Map<String, String>> orgMapList = new ArrayList<>();
		for (UserRole o : roles) {
			Map<String, String> orgMap = new HashMap<>();
			String role = o.toString();
			System.out.println("role: " + role);
			orgMap.put("id", role);
			orgMap.put("name", role);
			orgMapList.add(orgMap);
		}

		return orgMapList;
	}

	@RequestMapping(value = "adduser", method = { RequestMethod.POST }, headers = { "Content-type=application/json" })
	public @ResponseBody DataGridBean addOrg(@RequestBody AppUser o, HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException {
		List<String> msgs = validateFormPost(o);
		if (msgs.size() > 0) {
			handleResponse(response, HttpStatus.SC_EXPECTATION_FAILED, msgs);
			return null;
		}

		o = configEjb.saveAppUser(o);
		DataGridBean dgb = new DataGridBean();
		List<AppUser> list = configEjb.getAppUserList();
		dgb.setTotal(list.size());
		dgb.setRows(list);

		return dgb;
	}

	@RequestMapping(value = "useredit/{id}", method = { RequestMethod.GET })
	public @ResponseBody AppUser orgEdit(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException {
		AppUser o = configEjb.getAppUserByID(id);

		return o;
	}

	@RequestMapping(value = "userdelete/{id}", method = { RequestMethod.GET })
	public @ResponseBody AppUser orgDelete(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException {
		AppUser o = configEjb.getAppUserByID(id);
		o = configEjb.deleteAppUser(o);

		return o;
	}

	@RequestMapping(value = "resetpass", method = { RequestMethod.POST })
	public void resetPassword(HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {

		Integer id = Integer.valueOf(request.getParameter("userID"));
		String password = request.getParameter("resetPassword");
		String passwordMatch = request.getParameter("resetPasswordMatch");
		List<String> msgs = validatePasswordReset(password, passwordMatch);
		if (msgs.size() > 0) {
			handleResponse(response, HttpStatus.SC_EXPECTATION_FAILED, msgs);
			return;
		}

		AppUser o = configEjb.getAppUserByID(id);
		o = configEjb.saveAppUser(o);
		msgs.clear();
		msgs.add("Password successfully reset for login : " + o.getEmailAddress());
		handleResponse(response, HttpStatus.SC_ACCEPTED, msgs);
		return;
	}
	
	
	@RequestMapping(value = "firmusers/list", method = RequestMethod.GET, produces = "application/json")
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
    	
    	Integer firmId = 0;
    	HttpSession ses = request.getSession();
    	Object o = ses.getAttribute("LOGGEDIN_USER");
    	if(o instanceof FirmUser) {
    		FirmUser fu = (FirmUser)o;
    		firmId = fu.getFirmId();
    	}
    	
    	//Create page list data
    	List<FirmUser> firmUserList = createPaginationData(firstRow, pageDisplayLength, colName, sortOrder, firmId);
		
		//Search functionality: Returns filtered list based on search parameter
    	firmUserList = getListBasedOnSearchParameter(searchParameter, firmUserList);
		int total = configEjb.getFirmUserCount();
		
		GridJson<FirmUser> userJsonObject = new GridJson<>();
		//Set Total display record
		userJsonObject.setiTotalDisplayRecords(total);
		//Set Total record
		userJsonObject.setiTotalRecords(total);
		userJsonObject.setAaData(firmUserList);
		String json2 = "";
		try {
			@SuppressWarnings("rawtypes")
			JSONUtil<GridJson> jsonUtil = new JSONUtil<>(GridJson.class);
			json2 = jsonUtil.createJsonFromObject(userJsonObject);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("JSON: " + json2);
	
		return json2;
	}
	
	private List<FirmUser> getListBasedOnSearchParameter(
			String searchParameter, List<FirmUser> list) {
		if (null != searchParameter && !searchParameter.equals("")) {
			List<FirmUser> userListForSearch = new ArrayList<>();
			searchParameter = searchParameter.toUpperCase();
			for (FirmUser user : list) {
				if (user.getFirstName().toUpperCase().indexOf(searchParameter)!= -1 || user.getLastName().toUpperCase().indexOf(searchParameter)!= -1
						|| user.getCellPhone().toUpperCase().indexOf(searchParameter)!= -1 || user.getEmailAddress().toUpperCase().indexOf(searchParameter)!= -1
						) {
					userListForSearch.add(user);
				}
				
			}
			list = userListForSearch;
			userListForSearch = null;
		}
		return list;
	}
 
	private List<FirmUser> createPaginationData(int firstRow, Integer maxRow, String sortCol, String sortOrder, Integer firmId) {
		List<FirmUser> list = configEjb.getFirmUserList(firstRow, maxRow, sortCol, sortOrder, firmId);

		return list;
	}
	

	private List<String> validateFormPost(AppUser o) {
		List<String> validationMessages = new ArrayList<>();
		if (null != o) {
			if (o.getPassword().compareTo(o.getPasswordMatch()) != 0) {
				validationMessages.add("Passwords do not match");
			}
		}
		return validationMessages;
	}

	private List<String> validateLoginPost(FirmUser o) {
		List<String> validationMessages = new ArrayList<>();
		if (null != o) {
			if (o.getPassword() == null) {
				validationMessages.add("Please enter valid password.");
			}
			if (o.getEmailAddress() == null) {
				validationMessages.add("Please enter valid username (email).");
			}
		}

		return validationMessages;
	}

	private List<String> validatePasswordReset(String password, String matchPassword) {
		List<String> validationMessages = new ArrayList<>();
		if (password == null) {
			validationMessages.add("Password not eneterd.");
		}
		if (matchPassword == null) {
			validationMessages.add("Re-Type password not eneterd.");
		}
		if (password != null && matchPassword != null) {
			if (!matchPassword.equals(password)) {
				validationMessages.add("Password and Re-Type Password do not match.");
			}
		}
		return validationMessages;
	}
	
	
	private Firm getFirmForUser(FirmUser firmUser) {
		Firm firm = null;
		String emailAddress = firmUser.getEmailAddress();
		int atIndex = emailAddress.indexOf("@") + 1;
		if(atIndex> 0) {
			String domain = emailAddress.substring(atIndex);
			firm = configEjb.getFirmByDomain(domain);
		}
		
		return firm;
	}

}
