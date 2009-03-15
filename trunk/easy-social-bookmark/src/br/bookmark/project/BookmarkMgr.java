package br.bookmark.project;

import java.util.Collection;

import br.bookmark.models.Bookmark;
import br.bookmark.models.BookmarkDAO;


public class BookmarkMgr {
	private BookmarkDAO bookmarkDAO=null;
	public BookmarkMgr(BookmarkDAO bookmarkDAO){
		this.bookmarkDAO=bookmarkDAO;
	}
	
	public Collection<Bookmark> findBookmarks() throws Exception {
        return this.bookmarkDAO.findAll();
    }
}
