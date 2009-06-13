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
	private List<Bookmark> bookmarks = new ArrayList<Bookmark>();

	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<TagUser> tagsUser = new ArrayList<TagUser>();

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

	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}
	
	public void addBookmark(Bookmark bookmark) {
		this.bookmarks.add(bookmark);
	}

	public void setTagsUser(List<TagUser> tagsUser) {
		this.tagsUser = tagsUser;
	}

	public List<TagUser> getTagsUser() {
		return tagsUser;
	}

	public void addTagUser(TagUser tagUser) {
		this.tagsUser.add(tagUser);
	}

}
