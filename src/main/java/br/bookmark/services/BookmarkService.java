package br.bookmark.services;

import br.bookmark.models.Bookmark;

public interface BookmarkService {
	
	public Bookmark findById( String id );
	
	public void persist( Bookmark bookmark, String id );
	
	public void remove(long idBookmark);
	
	public void remove(Bookmark bookmark);

	public void addUser(Bookmark bookmark, long idUser);

	public void addCommunity(Bookmark bookmark, long idCommunity);
}
