
package br.bookmark.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected User userLogon;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
		this.userLogon = (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT); 
	}
}
