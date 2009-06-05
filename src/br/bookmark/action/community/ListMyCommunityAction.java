
package br.bookmark.action.community;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.action.user.BaseUserAction;
import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ListMyCommunityAction extends BaseUserAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	public String execute() throws Exception {
		User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		user = service.findById(""+user.getId());
		request.getSession(true).setAttribute(SecurityInterceptor.USER_OBJECT,user);
        return SUCCESS;
    }

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;
	}
	
	
}
