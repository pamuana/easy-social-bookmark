package br.bookmark.project;

import java.util.Collection;

import br.bookmark.models.Tag;
import br.bookmark.models.TagDAO;

public class TagMgr {
	private TagDAO tagDAO=null;
	public TagMgr(TagDAO tagDAO){
		this.tagDAO=tagDAO;
	}
	
	public Collection<Tag> findTags() throws Exception {
        return this.tagDAO.findAll();
    }
}
