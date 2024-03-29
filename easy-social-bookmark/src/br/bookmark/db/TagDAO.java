package br.bookmark.db;
import java.util.ArrayList;
import java.util.Collection;

import br.bookmark.db.util.DataBaseUtils;
import br.bookmark.db.util.Database;
import br.bookmark.db.util.GenericDAO;
import br.bookmark.models.Tag;


public class TagDAO extends GenericDAO<Tag> {

	public TagDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela,database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		DataBaseUtils.createTable(createConnection(), this.getPrefixoTabela()+"categorization", "(idTag LONG, idBookmark LONG)");
	}

	public Collection<Tag> findTagsByName(String name) throws Exception{
		return this.findCollectionByCriterio("name='"+name+"'");
	}
	
	public Collection<Tag> findTagsByIdUser(long idUser) throws Exception{
		return this.findCollectionByCriterio("idUser="+idUser);
	}
	
	public Collection<Tag> findTagsByIdCommunity(long idCommunity) throws Exception{
		return this.findCollectionByCriterio("idCommunity="+idCommunity);
	}
	
	public Collection<String> findIdBookmarksByIdTag(long idTag) throws Exception{
		return this.findField("idBookmark", this.getPrefixoTabela()+"categorization", "idTag="+idTag);
	}
	
	public Collection<String> findIdTagsByIdBookmark(long idBookmark) throws Exception{
		return this.findField("idTag", this.getPrefixoTabela()+"categorization", "idBookmark="+idBookmark);
	}
	
	public Collection<Tag> findTagsByIdBookmark(long idBookmark) throws Exception{
		return this.findCollectionByCriterio("id IN (SELECT idTag FROM "+this.getPrefixoTabela()+"categorization WHERE idBookmark="+idBookmark+")");
	}
	
	// --- Additional Methods for manager tags
	
	public void deleteTag(long idTag) throws Exception{
		String str = "DELETE FROM "+this.getPrefixoTabela()+"categorization"+" WHERE idTag="+idTag;
        createConnection().createStatement().executeUpdate(str);
        str = "DELETE FROM "+this.getPrefixoTabela()+"Tag"+" WHERE id="+idTag;
        createConnection().createStatement().executeUpdate(str);
	}
	
	public void assignBookmark(long idTag,long idBookmark) throws Exception{
		String tableName = this.getPrefixoTabela()+"categorization";
		if (this.count(tableName, "idTag="+idTag+" AND idBookmark="+idBookmark)==0){
			String str = "INSERT INTO "+tableName+" (idTag,idBookmark) 	VALUES("+idTag+","+idBookmark+")";
			createConnection().createStatement().executeUpdate(str);
		}
	}
	
	public void deassignBookmark(long idTag,long idBookmark) throws Exception{
		String tableName = this.getPrefixoTabela()+"categorization";
		String str = "DELETE FROM "+tableName+" WHERE idTag="+idTag+" AND idBookmark="+idBookmark;
		createConnection().createStatement().executeUpdate(str);
		if (this.count(tableName, "idTag="+idTag)==0){
			this.delete(idTag);
		}
	}
	
	public void deassignTag(long idBookmark) throws Exception{
		String tableName = this.getPrefixoTabela()+"categorization";
		String str = "DELETE FROM "+tableName+" WHERE idBookmark="+idBookmark;
		createConnection().createStatement().executeUpdate(str);
		this.deleteTagsWithoutCategorization();
	}

	protected void deleteTagsWithoutCategorization() throws Exception {
		String tableName = this.getPrefixoTabela()+"categorization";
		Collection<Tag> tags = this.findAll();
		for (Tag tag:tags){
			if (this.count(tableName, "idTag="+tag.getId())==0){
				this.delete(tag.getId());
			}
		}
	}
	
	public void assignBookmarkWithIdUser(String[] tags, long idBookmark,long idUser) throws Exception{
		Collection<Tag> tagsUser=this.findTagsByIdUser(idUser);
		Collection<String> tagsUserName=new ArrayList<String>();
		for (Tag tagUser:tagsUser){
			tagsUserName.add(tagUser.getName());
		}
		
    	for(String tagName : tags){
    		tagName=tagName.replaceAll(" ","");
    		if (!tagsUserName.contains(tagName)){
    			Tag tagNew=new Tag();
    			tagNew.setIdUser(idUser);
    			tagNew.setName(tagName);
    			this.save(tagNew);
    		}
    		
    		for (Tag tag:this.findTagsByName(tagName)){
    			if (idUser==tag.getIdUser()){
    				this.assignBookmark(tag.getId(), idBookmark);
    			} 
    		}
		}
	}
	
	public void assignBookmarkWithIdCommunity(String[] tags, long idBookmark,long idCommunity, Collection<Tag> tagsCommunity) throws Exception{
		Collection<String> tagsCommunityName=new ArrayList<String>();
		for (Tag tagCommunity:tagsCommunity){
			tagsCommunityName.add(tagCommunity.getName());
		}
		
    	for(String tagName : tags){
    		tagName=tagName.replaceFirst(" ", "");
    		tagName=tagName.replaceAll(" ","");
    		if (!tagsCommunityName.contains(tagName)){
    			Tag tagNew=new Tag();
    			tagNew.setIdCommunity(idCommunity);
    			tagNew.setName(tagName);
    			this.save(tagNew);
    		}
    		
    		for (Tag tag:this.findTagsByName(tagName)){
    			if (idCommunity==tag.getIdCommunity()){
    				this.assignBookmark(tag.getId(), idBookmark);
    			} 
    		}
		}
	}
}
