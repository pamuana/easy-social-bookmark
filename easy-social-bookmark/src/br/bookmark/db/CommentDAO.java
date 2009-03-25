package br.bookmark.db;
import java.util.Collection;

import br.bookmark.db.util.Database;
import br.bookmark.db.util.GenericDAO;
import br.bookmark.models.Comment;


public class CommentDAO extends GenericDAO<Comment> {

	public CommentDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela,database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		// TODO Auto-generated method stub		
	}
	
	public Collection<Comment> findCommentsByIdBookmark(long idBookmark) throws Exception{
		return this.findCollectionByCriterio("idBookmark="+idBookmark);
	}
	
	public Collection<Comment> findCommentsByIdUser(long idUser) throws Exception{
		return this.findCollectionByCriterio("idUser="+idUser);
	}
	
	// --- Additional Methods for manager communities
	
	public void deleteComment(long idComment) throws Exception{
		this.delete(idComment);
	} 

}
