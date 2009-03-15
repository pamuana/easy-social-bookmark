package br.bookmark.models;
import br.bookmark.db.DataBaseUtils;
import br.bookmark.db.Database;
import br.bookmark.db.GenericDAO;


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
