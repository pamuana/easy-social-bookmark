package br.bookmark.services;

import javax.persistence.EntityTransaction;

import br.bookmark.models.User;

public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
	
	public UserServiceImpl() {
		super();
		this.type = User.class;
	}
	
}
