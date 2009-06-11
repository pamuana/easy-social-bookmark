package br.bookmark.services;

import java.util.Map;

import br.bookmark.models.BookmarkPrivate;

public interface BookmarkPrivateService extends GenericService<BookmarkPrivate> {
	
	public Map<String,Long> getUserCloud(String idUser,long cssMaxIndex);
	
}
