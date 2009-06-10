package br.bookmark.action.bookmark;

import br.bookmark.action.BaseAction;
import br.bookmark.models.BookmarkPrivate;
import br.bookmark.models.BookmarkPublic;
import br.bookmark.services.BookmarkPrivateService;
import br.bookmark.services.BookmarkPublicService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseBookmarkPublicAction extends BaseAction implements ModelDriven<BookmarkPublic>, Preparable {

	private static final long serialVersionUID = 1L;

	protected BookmarkPublic bookmark;
	protected String idBookmark;
	protected BookmarkPublicService service;
	
	public BookmarkPublic getModel() {
		return this.bookmark;
	}

	public void setBookmarkPublicService(BookmarkPublicService service) {
		this.service = service;
	}

	public void prepare() throws Exception {
		if( this.idBookmark ==null || "".equals(this.idBookmark) ) {
			 this.bookmark = new BookmarkPublic();
		} else {
			this.bookmark = service.findById(this.idBookmark);
		}
	}
	
	// --------------------------------------------------- 
	
	public void setBookmark(BookmarkPublic bookmark) {
		this.bookmark = bookmark;
	}
	public BookmarkPublic getBookmark() {
		return bookmark;
	}
	
	public void setIdBookmark(String idBookmark) {
		this.idBookmark = idBookmark;
	}
	public String getIdBookmark() {
		return idBookmark;
	}

	public void setService(BookmarkPublicService service) {
		this.service = service;
	}
	public BookmarkPublicService getService() {
		return service;
	}

}
