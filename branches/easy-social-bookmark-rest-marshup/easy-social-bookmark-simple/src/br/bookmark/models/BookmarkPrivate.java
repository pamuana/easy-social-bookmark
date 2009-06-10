package br.bookmark.models;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BookmarkPrivate extends Bookmark {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idUser")
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
