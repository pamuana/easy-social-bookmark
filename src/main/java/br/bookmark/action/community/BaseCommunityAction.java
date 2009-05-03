package br.bookmark.action.community;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Community;
import br.bookmark.services.CommunityService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseCommunityAction extends BaseAction implements ModelDriven<Community>, Preparable {

	private static final long serialVersionUID = 1L;
	
	protected Community community;
    protected String idCommunity;
    protected CommunityService service;
    
    public Community getModel() {
        return community;
    }
    
    public void setCommunityService(CommunityService service) {
        this.service = service;
    }
    
    public void prepare() throws Exception {
        if( idCommunity==null || "".equals(idCommunity) ) {
            community = new Community();
        } else {
            community = service.findById(idCommunity);
        }
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
    
    public void setIdCommunity(String idCommunity) {
		this.idCommunity = idCommunity;
	}

	public String getIdCommunity() {
		return idCommunity;
	}
    

    

	
}
