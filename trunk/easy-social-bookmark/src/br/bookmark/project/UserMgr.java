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
}
