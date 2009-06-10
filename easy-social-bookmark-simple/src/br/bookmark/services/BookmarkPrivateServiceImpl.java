package br.bookmark.services;

import br.bookmark.models.BookmarkPrivate;

public class BookmarkPrivateServiceImpl extends GenericServiceImpl<BookmarkPrivate> implements BookmarkPrivateService {
	
	public BookmarkPrivateServiceImpl() {
		super();
		this.type = BookmarkPrivate.class;
	}

}
