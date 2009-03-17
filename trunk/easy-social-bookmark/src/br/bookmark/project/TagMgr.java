package br.bookmark.project;

import java.util.Collection;

import br.bookmark.models.Tag;
import br.bookmark.models.TagDAO;
import br.bookmark.models.User;

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
		return this.tagDAO.findTagsByName(name);
	}
	
	public Collection<Tag> findTagsByIdBookmark(String idBookmark) throws Exception{
		return this.tagDAO.findTagsByIdBookmark(Long.parseLong(idBookmark));
	}
	
	public Collection<Tag> findTagsByIdUser(String idUser) throws Exception{
		return this.tagDAO.findTagsByIdUser(Long.parseLong(idUser));
	}
	
	public void assignBookmark(String idTag, String idBookmark) throws Exception {
        this.tagDAO.assignBookmark(Long.parseLong(idTag) , Long.parseLong(idBookmark) );
    }
	
	public void save(Tag tag) throws Exception {
	        this.tagDAO.save(tag);
	}
	    
    public void delete(Long tagId) throws Exception {
        this.tagDAO.delete(tagId);
    }
}
