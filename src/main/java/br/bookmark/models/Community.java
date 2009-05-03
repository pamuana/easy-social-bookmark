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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="Community")
public class Community implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="id")
	private long id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="description")
    private String description;
    
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
    private List<User> users = new ArrayList<User>();
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Bookmark> bookmarks = new ArrayList<Bookmark>();
	
	@OneToMany(mappedBy="community",cascade=CascadeType.ALL)
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<User> getUsers() {
		return users;
	}
	public void addUser(User user){
		users.add(user);
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
