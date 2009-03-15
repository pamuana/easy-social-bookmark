package br.bookmark.project;

import java.util.Collection;

import br.bookmark.models.Community;
import br.bookmark.models.CommunityDAO;


public class CommunityMgr {
	private CommunityDAO communityDAO=null;
	public CommunityMgr(CommunityDAO communityDAO){
		this.communityDAO=communityDAO;
	}
	
	public Collection<Community> findCommunitys() throws Exception {
        return this.communityDAO.findAll();
    }
}
