
package br.bookmark.action.bookmark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Bookmark;
import br.bookmark.models.TagUser;
import br.bookmark.models.User;
import br.bookmark.services.BookmarkService;
import br.bookmark.services.TagService;
import br.bookmark.services.TagUserService;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ListMyBookmarkAction extends BaseAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;

	protected List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	private String tag;
	
	protected BookmarkService service;
	protected TagService tagService;
	protected TagUserService tagUserService;

	public void setBookmarkService(BookmarkService service) {
		this.service = service;
	}
	
	public void setTagService(TagService service) {
		this.tagService = service;
	}
	
	public void setTagUserService(TagUserService service) {
		this.tagUserService = service;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;		
	}

	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public List<Bookmark> getBookmarks() {
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
		
		if (tag!=null && !"".equals(tag)) {
			this.bookmarks = new ArrayList<Bookmark>();
			String idTag = ""+this.tagService.findByField("name", tag).getId();
			List<TagUser> tagsUser = tagUserService.listByCriteria(" idTag="+idTag+" AND idUser="+idUser+" ");
			for (TagUser tagUser : tagsUser) {
				this.bookmarks.add(tagUser.getBookmark());
			}
		}
		
		return SUCCESS;
	}



}
