package br.bookmark.services;

import br.bookmark.models.Comment;

// TODO revisar o método deve funcionar adicionando um idBookmark ao bookmark 
public class CommentServiceImpl extends GenericServiceImpl<Comment> implements CommentService {
	
	public CommentServiceImpl() {
		super();
		this.type = Comment.class;
	}

}
