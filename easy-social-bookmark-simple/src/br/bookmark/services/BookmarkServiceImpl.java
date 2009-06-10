package br.bookmark.services;

import javax.persistence.EntityTransaction;

import br.bookmark.models.Bookmark;

public class BookmarkServiceImpl extends GenericServiceImpl<Bookmark> implements BookmarkService {
	
	public BookmarkServiceImpl() {
		super();
		this.type = Bookmark.class;
	}



}
