package br.bookmark.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.util.RequiresAuthentication;


@RequiresAuthentication
public class LogoffAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    public String execute() throws Exception {
        request.getSession().invalidate();
        return SUCCESS;
    }
}