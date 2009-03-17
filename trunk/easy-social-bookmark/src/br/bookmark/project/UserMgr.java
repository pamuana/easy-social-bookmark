package br.bookmark.project;

import java.util.Collection;

import br.bookmark.models.User;
import br.bookmark.models.UserDAO;

public class UserMgr {
	private UserDAO userDAO=null;
	public UserMgr(UserDAO userDAO){
		this.userDAO=userDAO;
	}
	
	public Collection<User> findUsers() throws Exception {
        return this.userDAO.findAll();
    }
	
	public User validateUser(String login, String password) throws Exception  {
        User user = this.userDAO.findByLogin(login);
        if (user == null) return null;
        if (user.getPassword().equals(password)) return user;
        return null;
    }
	
    public User findById(String idUser) throws Exception {
        return this.userDAO.findById(Long.parseLong(idUser));
    }
    
    public User findByLogin(String login) throws Exception {
        return this.userDAO.findByLogin(login);
    }

    public void save(User user) throws Exception {
        this.userDAO.save(user);
    }
    
    public void delete(Long userId) throws Exception {
        this.userDAO.delete(userId);
    }
}
