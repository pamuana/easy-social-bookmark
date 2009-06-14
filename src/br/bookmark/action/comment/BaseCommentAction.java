package br.bookmark.action.comment;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Comment;
import br.bookmark.services.BookmarkService;
import br.bookmark.services.CommentService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseCommentAction extends BaseAction implements ModelDriven<Comment>, Preparable {

	private static final long serialVersionUID = 1L;

	protected Comment comment;
	protected String idComment;
	protected CommentService commentService;
	
	public Comment getModel() {
		return getComment();
	}

	public void setCommentService(CommentService service) {
		this.commentService = service;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Comment getComment() {
		return comment;
	}

	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}

	public String getIdComment() {
		return idComment;
	}

	public void prepare() throws Exception {
		if( this.idComment==null || "".equals(this.idComment) ) {
			this.comment = new Comment();
		} else {
			this.comment = commentService.findById(this.idComment);
		}
	}

	


}