package br.bookmark.services;

import br.bookmark.models.Comment;

public interface CommentService extends GenericService<Comment> {

	public void persistNative(Comment comment, String idBookmark);
}
