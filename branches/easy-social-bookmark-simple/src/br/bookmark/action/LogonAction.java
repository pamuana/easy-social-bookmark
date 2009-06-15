package br.bookmark.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import br.bookmark.models.Community;
import br.bookmark.models.User;
import br.bookmark.services.CommunityService;
import br.bookmark.services.TagUserService;
import br.bookmark.services.UserService;
import br.bookmark.util.DecisionTreeImpl;
import br.bookmark.util.SecurityInterceptor;
import br.usp.ime.collective.decisiontree.Attribute;
import br.usp.ime.collective.decisiontree.model.DecisionTree;

import javax.servlet.http.HttpServletRequest;


public class LogonAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private List<Community> communities = new ArrayList<Community>();

	protected UserService service;
	protected TagUserService tagUserService;
	protected CommunityService communityService;
	
	private HttpServletRequest request;

	public static final String FAILURE = "failed";

	public void setUserService(UserService service) {
		this.service = service;
	}
	
	public void setTagUserService(TagUserService service) {
		this.tagUserService = service;
	}
	
	public void setCommunityService(CommunityService service) {
		this.communityService = service;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;
	}

	public String getLogin() {
		return login;
	}
	
	@RequiredStringValidator(message="the field login is requiered")
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public String execute() throws Exception {
		try {
			User user = service.validateUser(login, password);

			if( user!=null && null!=login && !"".equals(login)
					&& password.equals(user.getPassword()) ) {
				request.getSession(true).setAttribute(SecurityInterceptor.USER_OBJECT,user);
				
				//..add cloud of tags in session variable cloudText
				String cloudText= tagUserService.getCloud(""+user.getId(),request.getContextPath()+"/bookmark/listMyBookmark.action?tag=");
				request.getSession(true).setAttribute("cloudText",cloudText);
				
				//..add cloud of tags in session variable cloudText
				String communityListText= communityService.getCommunityListText(""+user.getId(),request.getContextPath()+"/bookmark/listCommunityBookmark.action?idCommunity=");
				request.getSession(true).setAttribute("communityListText",communityListText);
				
				classifiedUserCommunity(user);
				
				
				return SUCCESS;
			} else {
				addActionError(getText("Authentification failed. Your login and password is wrong"));
				return FAILURE;
			}
		} catch (Exception e) {
			addActionError(getText("Authentification failed. The user doesn't exists"));
			return FAILURE;
		}
	}

	
	private void classifiedUserCommunity(User user) {
		DecisionTree dt;
		try {
			dt = DecisionTreeImpl.getInstance();
			Map<Attribute, Object> observation = new HashMap<Attribute, Object>();
			observation.put(dt.getAttributeSet().get(0), user.getAge());
			observation.put(dt.getAttributeSet().get(1), user.getCity());
			observation.put(dt.getAttributeSet().get(2), user.getState());
			observation.put(dt.getAttributeSet().get(3), user.getCountry());
			
			this.communities = new ArrayList<Community>();
			Map<Object, Double> results = dt.mdClassify(observation);
			for (Object element : results.keySet()) {
				this.communities.add(this.communityService.findByLikeField("name", element.toString()));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
