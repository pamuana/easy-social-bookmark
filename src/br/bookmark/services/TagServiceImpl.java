package br.bookmark.services;

import br.bookmark.models.Tag;

public class TagServiceImpl extends GenericServiceImpl<Tag> implements TagService {
	
	public TagServiceImpl() {
		super();
		this.type = Tag.class;
	}

}
