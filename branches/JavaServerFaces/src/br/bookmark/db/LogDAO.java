package br.bookmark.db;

import java.util.Collection;

import br.bookmark.db.util.Database;
import br.bookmark.db.util.GenericDAO;
import br.bookmark.models.Log;

public class LogDAO extends GenericDAO<Log> {

	public LogDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela, database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		
	}
	
	public Collection<Log> findLogsByIdUser(long idUser) throws Exception{
		return this.findCollectionByCriterio("idUser="+idUser);
	}
	
	public Collection<Log> findLogsByIdBookmark(long idBookmark) throws Exception{
		return this.findCollectionByCriterio("idBookmark="+idBookmark);
	}
	
	public Collection<Log> findLogsByUrl(String url) throws Exception{
		return this.findCollectionByCriterio("url='"+url+"'");
	}
	
	public Collection<Log> findLogsByDate(long date) throws Exception{
		return this.findCollectionByCriterio("date="+date);
	}
	
	// --- Additional Methods for manager logs
	
	public void deleteLog(long idLog) throws Exception{
		this.delete(idLog);
	}
}
