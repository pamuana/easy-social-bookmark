package br.bookmark.models;
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
}
