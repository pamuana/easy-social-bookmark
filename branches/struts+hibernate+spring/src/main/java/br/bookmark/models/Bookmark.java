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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity @Table(name="Bookmark") // @Inheritance(strategy=InheritanceType.JOINED)
public class Bookmark implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="id")
	private long id;
	
	@Column(name="url")
	private String url;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="tags")
	private String tags;
	
	@ManyToOne(cascade=CascadeType.ALL) @JoinColumn(name="idUser")
	private User user;
	
	@OneToMany(mappedBy="bookmark",cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTags() {
		return tags;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
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

}