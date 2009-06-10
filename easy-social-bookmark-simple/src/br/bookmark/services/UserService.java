package br.bookmark.services;

import br.bookmark.models.User;


public interface UserService extends GenericService<User>{
	
	public User validateUser(String login, String password);
	
}
