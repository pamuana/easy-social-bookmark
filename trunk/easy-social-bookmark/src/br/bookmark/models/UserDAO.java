package br.bookmark.models;
import br.bookmark.db.Database;
import br.bookmark.db.GenericDAO;


public class UserDAO extends GenericDAO<User> {

	public UserDAO(String prefixoTabela, Database database) throws Exception {
		super();
		init(prefixoTabela,database);
	}

	@Override
	protected void createAdditionalTables() throws Exception {
		// TODO Auto-generated method stub
	}

	public User findByLogin(String login) throws Exception {
		return findElementByCriterio("login='"+login+"'");
	}
	
	public User findByEmail(String email) throws Exception {
		return findElementByCriterio("email='"+email+"'");
	}

}
