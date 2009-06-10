package br.bookmark.action.bookmark;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Bookmark;
import br.bookmark.models.BookmarkPrivate;
import br.bookmark.services.BookmarkPrivateService;
import br.bookmark.services.BookmarkService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseBookmarkAction extends BaseAction implements ModelDriven<Bookmark>, Preparable {

	private static final long serialVersionUID = 1L;

	protected Bookmark bookmark;
	protected String idBookmark;
	protected BookmarkService service;
	
	public Bookmark getModel() {
		return this.bookmark;
	}

	public void setBookmarkService(BookmarkService service) {
		this.service = service;
	}

	public void prepare() throws Exception {
		if( this.idBookmark ==null || "".equals(this.idBookmark) ) {
			 this.bookmark = new Bookmark();
		} else {
			this.bookmark = service.findById(this.idBookmark);
		}
	}
	
	// --------------------------------------------------- 
	
	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}
	public Bookmark getBookmark() {
		return bookmark;
	}
	
	public void setIdBookmark(String idBookmark) {
		this.idBookmark = idBookmark;
	}
	public String getIdBookmark() {
		return idBookmark;
	}

	public void setService(BookmarkService service) {
		this.service = service;
	}
	public BookmarkService getService() {
		return service;
	}

}