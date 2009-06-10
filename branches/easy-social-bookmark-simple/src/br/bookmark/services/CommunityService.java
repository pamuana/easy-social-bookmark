package br.bookmark.services;

import java.util.List;

import br.bookmark.models.Community;

public interface CommunityService extends GenericService<Community> {
	
	public void persist(Community community, String id, String idUser);

	public List<Community> listCommunityWithOutSubscrition(String idUser);

	public void subscribeUser(Community community, String idUser);

	public void unsubscribeUser(Community community, String idUser);

	public void addBookmark(Community community, String idBookmark);
	
}
