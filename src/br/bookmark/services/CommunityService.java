package br.bookmark.services;

import java.util.List;

import br.bookmark.models.Community;

public interface CommunityService extends GenericService<Community> {

	public List<Community> listCommunityWithOutSubscrition(String idUser);

	public void addParticant(String idCommunity, String idUser);

	public List<Community> listByIdUser(String idUser);

}
