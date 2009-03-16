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
	
	public Tag findById(String idTag) throws Exception {
		return this.tagDAO.findById(Long.parseLong(idTag));
	}
	
	public Collection<Tag> findByName(String name) throws Exception{
		return this.tagDAO.findByName(name);
	}
}
