package br.bookmark.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name="User")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;

	@Column(name="name")
	private String name;

	@Column(name="login", unique=true)
	private String login;

	@Column(name="password")
	private String password;

	@Column(name="email", unique=true)
	private String email;

	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<BookmarkPrivate> bookmarks = new ArrayList<BookmarkPrivate>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	@RequiredStringValidator(message="Please enter a valid name")
	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}
	
	@RequiredStringValidator(message="Please enter a valid login")
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	
	@RequiredStringValidator(message="Please enter a valid password")
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	@RequiredStringValidator(message="Please enter a valid email")
	public void setEmail(String email) {
		this.email = email;
	}

	public void setBookmarks(List<BookmarkPrivate> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public List<BookmarkPrivate> getBookmarks() {
		return bookmarks;
	}
	
	public void addBookmark(BookmarkPrivate bookmark) {
		this.bookmarks.add(bookmark);
	}


}
