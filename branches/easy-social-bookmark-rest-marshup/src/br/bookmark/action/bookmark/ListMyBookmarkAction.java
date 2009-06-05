
package br.bookmark.action.bookmark;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Bookmark;
import br.bookmark.models.User;
import br.bookmark.services.BookmarkService;
import br.bookmark.services.UserService;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ListMyBookmarkAction extends BaseAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;

	protected List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	protected BookmarkService service;
	protected HttpServletRequest request;
	
	public void setBookmarkService(BookmarkService service) {
		this.service = service;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;		
	}
	
	/*
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

	*/
		
	
	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public String execute() throws Exception{
		String idUser = ""+((User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT)).getId();
		this.bookmarks = service.listByField("idUser", idUser);
		return SUCCESS;
	}

}
