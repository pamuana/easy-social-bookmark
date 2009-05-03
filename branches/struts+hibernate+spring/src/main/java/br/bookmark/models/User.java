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

@Entity @Table(name="User")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="id")
	private long id;
	
	@Column(name="name")
    private String name;
    
	@Column(name="login")
	private String login;
    
	@Column(name="password")
	private String password;
    
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Participant> participants = new ArrayList<Participant>();
	
    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
		
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}
	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}
	public void addBookmark(Bookmark bookmark){
		this.bookmarks.add(bookmark);
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void addComment(Comment comment){
		this.comments.add(comment);
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	public List<Participant> getParticipants() {
		return participants;
	}
	public void addParticipant(Participant participant){
		this.participants.add(participant);
	}

}
