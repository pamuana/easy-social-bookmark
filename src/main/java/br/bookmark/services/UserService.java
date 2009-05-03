package br.bookmark.services;

import br.bookmark.models.User;


public interface UserService {
	
	public User findById( String id );
	
	public void persist( User user, String id );
	
	public User validateUser(String login, String password);
}
