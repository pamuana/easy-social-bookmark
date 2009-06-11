
package br.bookmark.action.bookmark;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.action.BaseAction;
import br.bookmark.models.BookmarkPrivate;
import br.bookmark.models.User;
import br.bookmark.services.BookmarkPrivateService;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ListMyBookmarkAction extends BaseAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;

	protected List<BookmarkPrivate> bookmarks = new ArrayList<BookmarkPrivate>();
	protected BookmarkPrivateService service;
	protected HttpServletRequest request;
	private String tag;

	public void setBookmarkPrivateService(BookmarkPrivateService service) {
		this.service = service;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;		
	}

	public void setBookmarks(List<BookmarkPrivate> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public List<BookmarkPrivate> getBookmarks() {
		return bookmarks;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public String execute() throws Exception{
		String idUser = ""+((User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT)).getId();
		this.bookmarks = service.listByField("idUser", idUser);
		if (tag!=null && !"".equals(tag)) this.bookmarks = service.listByCriteria("idUser="+idUser+" AND tags LIKE '%"+tag+"%'");
		return SUCCESS;
	}



}
