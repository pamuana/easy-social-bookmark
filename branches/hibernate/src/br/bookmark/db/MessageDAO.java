package br.bookmark.db;
import java.util.Collection;

import br.bookmark.db.util.Database;
import br.bookmark.db.util.GenericDAO;
import br.bookmark.models.Message;


public class MessageDAO extends GenericDAO<Message> {

	public MessageDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela,database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		// TODO Auto-generated method stub
	}
	
	public Collection<Message> findMessagesByIdUser(long idUser) throws Exception{
		return this.findCollectionByCriterio("idUser="+idUser);
	}
	
	public Collection<Message> findMessagesByIdCommunity(long idCommunity) throws Exception{
		return this.findCollectionByCriterio("idCommunity="+idCommunity);
	}
	
	// --- Additional Methods for manager communities
	
	public void deleteMessage(long idMessage) throws Exception{
		this.delete(idMessage);
	}
}
