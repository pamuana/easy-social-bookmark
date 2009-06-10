package br.bookmark.services;

import br.bookmark.models.Bookmark;

public interface BookmarkService extends GenericService<Bookmark> {

	public void addUser(Bookmark bookmark, String idUser);

	public void addCommunity(Bookmark bookmark, String idCommunity);
	
}
