package br.bookmark.db;
import java.util.Collection;

import br.bookmark.db.util.Database;
import br.bookmark.db.util.GenericDAO;
import br.bookmark.models.Bookmark;


public class BookmarkDAO extends GenericDAO<Bookmark> {

	public BookmarkDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela,database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		// TODO Auto-generated method stub		
	}

	public Collection<Bookmark> findByName(String name) throws Exception {
		return this.findCollectionByCriterio("name='"+name+"'");
	}

	public Collection<Bookmark> findByUrl(String url) throws Exception {
		return this.findCollectionByCriterio("url='"+url+"'");
	}
	
	public Collection<Bookmark> findBookmarksByIdCommunity(long idCommunity) throws Exception {
		return this.findCollectionByCriterio("idCommunity="+idCommunity);
	}
	
	public Collection<Bookmark> findBookmarksByIdUser(long idUser) throws Exception{
		return this.findCollectionByCriterio("idUser="+idUser);
	}
	
	// --- Additional Methods for manager communities
	
	public void deleteBookmark(long idBookmark) throws Exception{
		this.delete(idBookmark);
	} 
}
