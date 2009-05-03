package br.bookmark.services;

import br.bookmark.models.Comment;

public interface CommentService {
	
	public Comment findById( String id );
	
	public void persist( Comment comment, String id );
	
	public void remove(Comment comment);

	public void persistNative(Comment comment, String idBookmark);
}
