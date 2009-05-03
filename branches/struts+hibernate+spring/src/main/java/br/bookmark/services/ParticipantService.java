package br.bookmark.services;

import br.bookmark.models.Participant;

public interface ParticipantService {
	
	public Participant findById( String id );
	
	public void persist( Participant participant, String id );
	
	public void remove(Participant participant);
}
