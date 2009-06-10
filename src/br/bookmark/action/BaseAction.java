
package br.bookmark.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest; 
	}
}
