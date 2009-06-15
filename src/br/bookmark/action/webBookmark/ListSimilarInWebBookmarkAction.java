package br.bookmark.action.webBookmark;

import java.util.ArrayList;
import java.util.List;

import br.bookmark.action.BaseAction;
import br.bookmark.models.WebBookmark;
import br.bookmark.services.WebBookmarkService;

public class ListSimilarInWebBookmarkAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	protected List<WebBookmark> bookmarks = new ArrayList<WebBookmark>();
	protected WebBookmarkService webBookmarkService;
	protected String idBookmark;
	
	public void setBookmarks(List<WebBookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}
	public List<WebBookmark> getBookmarks() {
		return bookmarks;
	}
	
	public void setWebBookmarkService(WebBookmarkService webBookmarkService) {
		this.webBookmarkService = webBookmarkService;
	}
	public WebBookmarkService getWebBookmarkService() {
		return webBookmarkService;
	}
	
	public void setIdBookmark(String idBookmark) {
		this.idBookmark = idBookmark;
	}
	public String getIdBookmark() {
		return idBookmark;
	}
	
	public String execute() throws Exception{
		this.bookmarks = this.webBookmarkService.listByField("idBookmark", this.idBookmark);
		return SUCCESS;
	}
	
}
