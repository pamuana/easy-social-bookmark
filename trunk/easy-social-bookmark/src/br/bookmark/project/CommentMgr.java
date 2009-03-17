package br.bookmark.project;

import java.util.Collection;

import br.bookmark.models.Comment;
import br.bookmark.models.CommentDAO;
import br.bookmark.models.User;

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
	
	public void save(Comment comment) throws Exception {
        this.commentDAO.save(comment);
    }
    
    public void delete(String idComment) throws Exception {
        this.commentDAO.deleteComment(Long.parseLong(idComment));
    }
	
}
