package br.bookmark.services;

import br.bookmark.models.TagUser;

public interface TagUserService extends GenericService<TagUser> {

	public void persist(String idUser, String idTag, String idBookmark);

	public void removeByBookmark(String idUser, String idBookmark);

	public String getCloud(String idUser);

	public String getCloud(String idUser, String href);
	
	public String getCloudShared(String href);

	public String getCloudCommunity(String idCommunity, String href);

}
