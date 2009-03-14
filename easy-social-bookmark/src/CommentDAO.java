import commons.db.Database;
import commons.db.GenericDAO;


public class CommentDAO extends GenericDAO<Comment> {

	public CommentDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela,database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		// TODO Auto-generated method stub		
	}

}
