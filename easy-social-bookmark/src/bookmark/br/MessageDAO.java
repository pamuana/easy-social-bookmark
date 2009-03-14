package bookmark.br;
import commons.db.Database;
import commons.db.GenericDAO;


public class MessageDAO extends GenericDAO<Message> {

	public MessageDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela,database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		// TODO Auto-generated method stub
	}

}
