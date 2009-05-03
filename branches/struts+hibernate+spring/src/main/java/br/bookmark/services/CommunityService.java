package br.bookmark.services;

import java.util.List;

import br.bookmark.models.Community;
import br.bookmark.models.Role;
import br.bookmark.models.User;

public interface CommunityService {
	
	public Community findById( String id );
	
	public void persist( Community community, String id );
	
	public void persist(Community community, String id, String idUser);
	
	public void remove(Community community);
	
	public void remove(long idCommunity);
	
	public List<Community> listAll();
	
	public List<Community> listCommunityWithOutSubscrition(String idUser);

	public void subscribeUser(Community community, long idUser);

	public void unsubscribeUser(Community community, long idUser);

	public void addBookmark(Community community, String idBookmark);
	
}
