package br.bookmark.models;
import java.sql.SQLException;
import java.util.Collection;

import br.bookmark.db.DataBaseUtils;
import br.bookmark.db.Database;
import br.bookmark.db.GenericDAO;


public class CommunityDAO extends GenericDAO<Community> {

	public CommunityDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela, database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		DataBaseUtils.createTable(createConnection(), this.getPrefixoTabela()+"participant", "(idCommunity LONG, idUser LONG)");
	}

	public Collection<Community> findByName(String name) throws Exception {
		return this.findCollectionByCriterio("name='"+name+"'");
	}

	public Collection<Community> findByIdParent(long idParent) throws Exception {
		return this.findCollectionByCriterio("idParent="+idParent);
	}

	public Collection<String> findIdUsersByIdCommunity(long idCommunity) throws Exception{
		return this.findField("idUser", this.getPrefixoTabela()+"participant", "idCommunity="+idCommunity);
	}
	
	public Collection<String> findIdCommunitiesByIdUser(long idUser) throws Exception{
		return this.findField("idCommunity", this.getPrefixoTabela()+"participant", "idUser="+idUser);
	}
	
	public Collection<Community> findCommunitiesByIdUser(long idUser) throws Exception{
		return this.findCollectionByCriterio("idCommunity IN (SELECT idCommunity FROM "+this.getPrefixoTabela()+"participant WHERE idUser="+idUser+")");
	}
	
	// --- Additional Methods for manager communities
	
	public void deleteCommunity(long idCommunity) throws Exception{
		String str = "DELETE FROM "+this.getPrefixoTabela()+"participant"+" WHERE idCommunity="+idCommunity;
        createConnection().createStatement().executeUpdate(str);
        str = "DELETE FROM "+this.getPrefixoTabela()+"Community"+" WHERE id="+idCommunity;
        createConnection().createStatement().executeUpdate(str);
	}
	
	public void deassignCommunity(long idCommunity,long idUser) throws Exception{
		String tableName = this.getPrefixoTabela()+"participant";
		String str = "DELETE FROM "+tableName+" WHERE idUser="+idUser+" AND idCommunity="+idCommunity;
		createConnection().createStatement().executeUpdate(str);
	}
	
	public void deassignCommunities(long idUser) throws Exception{
		String tableName = this.getPrefixoTabela()+"participant";
		String str = "DELETE FROM "+tableName+" WHERE idUser="+idUser;
		createConnection().createStatement().executeUpdate(str); 
	}
}
