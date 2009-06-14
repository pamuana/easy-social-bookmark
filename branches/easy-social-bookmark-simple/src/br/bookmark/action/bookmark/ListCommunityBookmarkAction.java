
package br.bookmark.action.bookmark;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Bookmark;
import br.bookmark.services.BookmarkService;
import br.bookmark.services.TagService;
import br.bookmark.services.TagUserService;

@ParentPackage("base-package")
public class ListCommunityBookmarkAction extends BaseAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;

	protected List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	protected String tag;
	protected String idCommunity;
	protected TagUserService tagUserService;
	protected TagService tagService;
	
	protected BookmarkService service;

	public void setBookmarkService(BookmarkService service) {
		this.service = service;
	}
	
	public void setTagUserService(TagUserService service) {
		this.tagUserService = service;
	}
	
	public void setTagService(TagService service) {
		this.tagService = service;
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
	
	public void setIdCommunity(String idCommunity) {
		this.idCommunity = idCommunity;
	}

	public String getIdCommunity() {
		return idCommunity;
	}

	public String execute() throws Exception{
		
		this.bookmarks = service.listByIdCommunity(this.idCommunity);
		
		if (tag!=null && !"".equals(tag)) {
			String idTag = ""+this.tagService.findByField("name", tag).getId();
			this.bookmarks = service.listByIdCommunity(this.idCommunity,idTag);
		}
		
		String cloudText= tagUserService.getCloudCommunity(this.idCommunity,request.getContextPath()+"/bookmark/listCommunityBookmark.action?idCommunity="+this.idCommunity+"&tag=");
		request.getSession(true).setAttribute("cloudText",cloudText);
		
		return SUCCESS;
	}
	
}
