package br.bookmark.db;
import br.bookmark.db.util.Database;
import br.bookmark.db.util.GenericDAO;
import br.bookmark.models.User;


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
	
	public User validateUser(String login, String password) throws Exception  {
        User user = this.findByLogin(login);
        if (user == null) return null;
        if (user.getPassword().equals(password)) return user;
        return null;
    }
}
