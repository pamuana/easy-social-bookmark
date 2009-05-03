package br.bookmark.action;

import org.apache.struts2.interceptor.ServletRequestAware;

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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() throws Exception {
        User user = service.validateUser(login, password);
        if( user!=null && null!=login && !"".equals(login)
                && password.equals(user.getPassword()) ) {
            request.getSession(true).setAttribute(SecurityInterceptor.USER_OBJECT,user);
            return SUCCESS;
        } else {
            addActionError(getText("auth.failed"));
            return FAILURE;
        }
    }
}
