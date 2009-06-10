
package br.bookmark.action.community;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.config.ParentPackage;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Bookmark;
import br.bookmark.models.Community;
import br.bookmark.services.BookmarkService;

@ParentPackage("base-package")
public class ListCommunityBookmarkAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private List<Bookmark> bookmarks;
	private String idCommunity;
	private BookmarkService service;

	public void setBookmarkService(BookmarkService service) {
		this.service = service;
	}

	
	public String execute() throws Exception {
		//bookmarks = service.findById(idCommunity).getBookmarks();
		this.bookmarks = new ArrayList<Bookmark>();
		for (Bookmark  bookmark: service.listAll()) {
			for (Community community:bookmark.getCommunities()) {
				if (idCommunity.equals(community.getId()+"")) {
					this.bookmarks.add(bookmark);
					break;
				}
			}
		}
		//service.findById(idCommunity)
		//this.setBookmarks();
		return SUCCESS;
	}


	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}


	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}


	public void setIdCommunity(String idCommunity) {
		this.idCommunity = idCommunity;
	}


	public String getIdCommunity() {
		return idCommunity;
	}



}
