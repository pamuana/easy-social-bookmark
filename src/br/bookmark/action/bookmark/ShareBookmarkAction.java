
package br.bookmark.action.bookmark;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.config.ParentPackage;

import br.bookmark.models.Participant;

@ParentPackage("base-package")
public class ShareBookmarkAction extends BaseBookmarkAction{

	private static final long serialVersionUID = 1L;
	
	private List<Participant> participants = new ArrayList<Participant>();
	
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public String execute() throws Exception {
		this.participants = this.bookmark.getUser().getParticipants();
		return SUCCESS;
	}

}
