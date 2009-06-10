package br.bookmark.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name="Comment")
public class Comment implements Serializable{	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="text")
	private String text;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idBookmark")
	private Bookmark bookmark;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idUser")
	private User user;
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	@RequiredStringValidator(message="the field text is requiered")
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}
	public Bookmark getBookmark() {
		return bookmark;
	}
	
}
