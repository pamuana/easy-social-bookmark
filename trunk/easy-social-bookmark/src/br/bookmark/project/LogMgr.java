package br.bookmark.project;

import java.util.Collection;
import java.util.Date;

import br.bookmark.models.Log;
import br.bookmark.models.LogDAO;

public class LogMgr {
	private LogDAO logDAO=null;
	
	public LogMgr(LogDAO logDAO){
		this.logDAO=logDAO;
	}
	
	public Collection<Log> findLogs() throws Exception{
		return this.logDAO.findAll();
	}
	
	public Collection<Log> findByUrl(String url) throws Exception{
		return this.logDAO.findLogsByUrl(url);
	}
	
	public Collection<Log> findByIdBookmark(String idBookmark) throws Exception{
		return this.logDAO.findLogsByIdBookmark(Long.parseLong(idBookmark));
	}
	
	public Collection<Log> findByIdUser(String idUser) throws Exception{
		return this.logDAO.findLogsByIdUser(Long.parseLong(idUser));
	}
	
	public Collection<Log> findByDate(Date date) throws Exception{
		return this.logDAO.findLogsByDate(date.getTime());
	}
}
