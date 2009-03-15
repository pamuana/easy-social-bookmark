package br.bookmark.models;

import java.util.Collection;

public class UserMgr {
	private UserDAO userDAO=null;
	public UserMgr(UserDAO userDAO){
		this.userDAO=userDAO;
	}
	
	public Collection<User> findRoles() throws Exception {
        return this.userDAO.findAll();
    }
}
