package br.bookmark.action.bookmark;

import br.bookmark.action.BaseAction;
import br.bookmark.models.BookmarkPrivate;
import br.bookmark.services.BookmarkPrivateService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseBookmarkPrivateAction extends BaseAction implements ModelDriven<BookmarkPrivate>, Preparable {

	private static final long serialVersionUID = 1L;

	protected BookmarkPrivate bookmark;
	protected String idBookmark;
	protected BookmarkPrivateService service;
	
	public BookmarkPrivate getModel() {
		return this.bookmark;
	}

	public void setBookmarkPrivateService(BookmarkPrivateService service) {
		this.service = service;
	}

	public void prepare() throws Exception {
		if( this.idBookmark ==null || "".equals(this.idBookmark) ) {
			 this.bookmark = new BookmarkPrivate();
		} else {
			this.bookmark = service.findById(this.idBookmark);
		}
	}
	
	// --------------------------------------------------- 
	
	public void setBookmark(BookmarkPrivate bookmark) {
		this.bookmark = bookmark;
	}
	public BookmarkPrivate getBookmark() {
		return bookmark;
	}
	
	public void setIdBookmark(String idBookmark) {
		this.idBookmark = idBookmark;
	}
	public String getIdBookmark() {
		return idBookmark;
	}

	public void setService(BookmarkPrivateService service) {
		this.service = service;
	}
	public BookmarkPrivateService getService() {
		return service;
	}

}
