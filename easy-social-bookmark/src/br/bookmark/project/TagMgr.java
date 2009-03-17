package br.bookmark.project;

import java.util.ArrayList;
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
	
	public void assignBookmarkWithIdUser(String[] tags, String idBookmark,String idUser) throws Exception{
		Collection<Tag> tagsUser=this.findTagsByIdUser(idUser);
		Collection<String> tagsUserName=new ArrayList<String>();
		for (Tag tagUser:tagsUser){
			tagsUserName.add(tagUser.getName());
		}
		
    	for(String tagName : tags){
    		tagName=tagName.replaceAll(" ","");
    		if (!tagsUserName.contains(tagName)){
    			Tag tagNew=new Tag();
    			tagNew.setIdUser(Long.parseLong(idUser));
    			tagNew.setName(tagName);
    			this.save(tagNew);
    		}
    		
    		for (Tag tag:this.findByName(tagName)){
    			if (Long.parseLong(idUser)==tag.getIdUser()){
    				this.assignBookmark(""+tag.getId(), idBookmark);
    			} 
    		}
		}
	}
	
	public void assignBookmarkWithIdCommunity(String[] tags, String idBookmark,String idCommunity) throws Exception{
		Collection<Tag> tagsCommunity=this.findTagsByIdCommunity(idCommunity);
		Collection<String> tagsCommunityName=new ArrayList<String>();
		for (Tag tagCommunity:tagsCommunity){
			tagsCommunityName.add(tagCommunity.getName());
		}
		
    	for(String tagName : tags){
    		tagName=tagName.replaceAll(" ","");
    		if (!tagsCommunityName.contains(tagName)){
    			Tag tagNew=new Tag();
    			tagNew.setIdCommunity(Long.parseLong(idCommunity));
    			tagNew.setName(tagName);
    			this.save(tagNew);
    		}
    		
    		for (Tag tag:this.findByName(tagName)){
    			if (Long.parseLong(idCommunity)==tag.getIdCommunity()){
    				this.assignBookmark(""+tag.getId(), idBookmark);
    			} 
    		}
		}
	}
	    
    public Collection<Tag> findTagsByIdCommunity(String idCommunity) throws Exception {
		return this.tagDAO.findTagsByIdCommunity(Long.parseLong(idCommunity));
	}

	public void delete(Long tagId) throws Exception {
        this.tagDAO.delete(tagId);
    }
}
