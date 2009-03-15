package br.bookmark.project;

import java.util.Collection;

import br.bookmark.models.Message;
import br.bookmark.models.MessageDAO;

public class MessageMgr {
	private MessageDAO messageDAO=null;
	public MessageMgr(MessageDAO messageDAO){
		this.messageDAO=messageDAO;
	}
	
	public Collection<Message> findMessages() throws Exception {
        return this.messageDAO.findAll();
    }
}
