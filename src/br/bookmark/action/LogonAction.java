package br.bookmark.action;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import br.bookmark.models.User;
import br.bookmark.services.UserService;
import br.bookmark.util.SecurityInterceptor;

import javax.servlet.http.HttpServletRequest;


public class LogonAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;

	protected UserService service;
	private HttpServletRequest request;

	public static final String FAILURE = "failed";

	public void setUserService(UserService service) {
		this.service = service;
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

	public String execute() throws Exception {
		try {
			User user = service.validateUser(login, password);

			if( user!=null && null!=login && !"".equals(login)
					&& password.equals(user.getPassword()) ) {
				request.getSession(true).setAttribute(SecurityInterceptor.USER_OBJECT,user);
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
}
