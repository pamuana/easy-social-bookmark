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
	
	public Message findById(String idMessage) throws Exception {
		return this.messageDAO.findById(Long.parseLong(idMessage));
	}
	
	public Collection<Message> findMessagesByIdCommunity(String idCommunity) throws Exception {
        return this.messageDAO.findMessagesByIdCommunity(Long.parseLong(idCommunity));
    }
	
	public void save(Message message) throws Exception {
        this.messageDAO.save(message);
    }
    
    public void delete(Long messageId) throws Exception {
        this.messageDAO.delete(messageId);
    }
}
