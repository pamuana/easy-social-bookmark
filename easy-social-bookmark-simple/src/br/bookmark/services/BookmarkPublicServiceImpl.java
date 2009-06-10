package br.bookmark.services;

import br.bookmark.models.BookmarkPublic;

public class BookmarkPublicServiceImpl extends GenericServiceImpl<BookmarkPublic> implements BookmarkPublicService {
	
	public BookmarkPublicServiceImpl() {
		super();
		this.type = BookmarkPublic.class;
	}

}
