package br.bookmark.services;

import java.util.List;

import br.bookmark.models.Bookmark;

public interface BookmarkService extends GenericService<Bookmark> {

	public List<Bookmark> listByIdCommunity(String idCommunity);

	public List<Bookmark> listByIdCommunity(String idCommunity, String idTag);

}
