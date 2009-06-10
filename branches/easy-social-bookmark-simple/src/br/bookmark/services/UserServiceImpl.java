package br.bookmark.services;

import br.bookmark.models.User;

public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
	
	public UserServiceImpl() {
		super();
		this.type = User.class;
	}
	
	public User validateUser(String login, String password) {
		User user = this.findByField("login", login);
		if (user!=null && password.equals(user.getPassword())) {
			return user;
		}else {
			return null;
		}
	}
}
