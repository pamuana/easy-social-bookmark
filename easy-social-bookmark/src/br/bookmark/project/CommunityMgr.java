package br.bookmark.project;

import java.util.ArrayList;
import java.util.Collection;

import br.bookmark.models.Community;
import br.bookmark.models.CommunityDAO;
import br.bookmark.models.User;
import br.bookmark.models.UserDAO;


public class CommunityMgr {
	private CommunityDAO communityDAO=null;
	private UserDAO userDAO=null;
	public CommunityMgr(CommunityDAO communityDAO,UserDAO userDAO){
		this.communityDAO=communityDAO;
		this.userDAO=userDAO;
	}
	
	public Collection<Community> findCommunities() throws Exception {
        return this.communityDAO.findAll();
    }
	
	public Community findById(String idCommunity) throws Exception{
		return this.communityDAO.findById(Long.parseLong(idCommunity));
	}
	
	public Collection<Community> findByName(String name) throws Exception{
		return this.communityDAO.findByName(name);
	}
	
	public Collection<Community> findSubCommunity(Community community) throws Exception {
		return this.findSubCommunity(""+community.getId());
	}
	
	public Collection<Community> findSubCommunity(String idCommunity) throws Exception{
		return this.communityDAO.findByIdParent(Long.parseLong(idCommunity));
	}
	
	public Collection<User> findUsersByCommunity(Community community) throws Exception{
		return this.findUsersByIdCommunity(""+community.getId());
	}
	
	public Collection<User> findUsersByIdCommunity(String idCommunity) throws Exception{
		Collection<String> idUsers=this.communityDAO.findIdUsersByIdCommunity(Long.parseLong(idCommunity));
		Collection<User> users=new ArrayList<User>();
		for (String idUser:idUsers){
			users.add(this.userDAO.findById(Long.parseLong(idUser)));
		}
		return users;
	}
	
	public Collection<Community> findCommunitiesByIdUser(String idUser) throws Exception{
		return this.communityDAO.findCommunitiesByIdUser(Long.parseLong(idUser));
	}
	
	public Collection<String> findIdCommunitiesByIdUser(String idUser) throws Exception{
		return this.communityDAO.findIdCommunitiesByIdUser(Long.parseLong(idUser));
	}
	
	public void deleteCommunity(String idCommunity) throws Exception{
		 this.communityDAO.deleteCommunity(Long.parseLong(idCommunity));
	}
	
	public void deassignCommunity(String idCommunity,String idUser) throws Exception{
		this.communityDAO.deassignCommunity(Long.parseLong(idCommunity), Long.parseLong(idUser));
	}
}
