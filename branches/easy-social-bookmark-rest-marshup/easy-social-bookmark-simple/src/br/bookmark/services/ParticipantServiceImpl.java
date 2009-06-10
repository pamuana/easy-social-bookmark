package br.bookmark.services;

import br.bookmark.models.Participant;

public class ParticipantServiceImpl extends GenericServiceImpl<Participant> implements ParticipantService {

	public ParticipantServiceImpl() {
		super();
		this.type = Participant.class;
	}

}
