package br.bookmark.project;

import java.util.Collection;

import br.bookmark.models.Comment;
import br.bookmark.models.CommentDAO;

public class CommentMgr {
	private CommentDAO commentDAO=null;
	public CommentMgr(CommentDAO commentDAO){
		this.commentDAO=commentDAO;
	}
	
	public Collection<Comment> findComments() throws Exception {
        return this.commentDAO.findAll();
    }
	
	public Comment findById(String idComment) throws Exception {
		return this.commentDAO.findById(Long.parseLong(idComment));
	}
	
	public Collection<Comment> findCommentsByIdBookmark(String idBookmark) throws Exception {
		return this.commentDAO.findCommentsByIdBookmark(Long.parseLong(idBookmark));
	}
	
}
