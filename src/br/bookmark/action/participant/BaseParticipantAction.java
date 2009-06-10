package br.bookmark.action.participant;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Participant;
import br.bookmark.services.ParticipantService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseParticipantAction extends BaseAction implements ModelDriven<Participant>, Preparable {

	private static final long serialVersionUID = 1L;
	
	protected Participant participant;
    protected String idParticipant;
    protected ParticipantService service;
    
    public Participant getModel() {
        return participant;
    }
    
    public void setParticipantService(ParticipantService service) {
        this.service = service;
    }
    
    public void prepare() throws Exception {
        if( idParticipant==null || "".equals(idParticipant) ) {
            participant = new Participant();
        } else {
            participant = service.findById(idParticipant);
        }
    }

    public Participant getParticipant() {
        return participant;
    }
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
    
    public void setIdParticipant(String idParticipant) {
		this.idParticipant = idParticipant;
	}
	public String getIdParticipant() {
		return idParticipant;
	}
    
}
