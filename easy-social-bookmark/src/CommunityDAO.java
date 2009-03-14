import commons.db.DataBaseUtils;
import commons.db.Database;
import commons.db.GenericDAO;


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
