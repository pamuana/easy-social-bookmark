package bookmark.br;
import commons.db.DataBaseUtils;
import commons.db.Database;
import commons.db.GenericDAO;


public class TagDAO extends GenericDAO<Tag> {

	public TagDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela,database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		DataBaseUtils.createTable(createConnection(), this.getPrefixoTabela()+"categorization", "(idTag LONG, idBookmark LONG)");
	}

}
