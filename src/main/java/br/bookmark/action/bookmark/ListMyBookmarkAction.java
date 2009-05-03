
package br.bookmark.action.bookmark;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.action.BaseAction;
import br.bookmark.models.User;
import br.bookmark.services.UserService;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ListMyBookmarkAction extends BaseAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private UserService service;
	private HttpServletRequest request;

	public void setUserService(UserService service) {
        this.service = service;
    }

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;		
	}
	
	public String execute() throws Exception {
		User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		user = service.findById(user.getId()+"");
		request.getSession(true).setAttribute(SecurityInterceptor.USER_OBJECT,user);
        return SUCCESS;
    }
	
	
}
