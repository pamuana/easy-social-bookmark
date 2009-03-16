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
	
	public Bookmark findById(String idBookmark) throws Exception{
		return this.bookmarkDAO.findById(Long.parseLong(idBookmark));
	}
	
	public Collection<Bookmark> findByName(String name) throws Exception{
		return this.bookmarkDAO.findByName(name);
	}
	
	public Collection<Bookmark> findByUrl(String url) throws Exception{
		return this.bookmarkDAO.findByUrl(url);
	}
	
	public Collection<Bookmark> findBookmarksByIdUser(String idUser) throws Exception{
		return this.bookmarkDAO.findBookmarksByIdUser(Long.parseLong(idUser));
	}
	
	public Collection<Bookmark> findBookmarksByIdCommunity(String idCommunity) throws Exception{
		return this.bookmarkDAO.findBookmarksByIdCommunity(Long.parseLong(idCommunity));
	}
}
