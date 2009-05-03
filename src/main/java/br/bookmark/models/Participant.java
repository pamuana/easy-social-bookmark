package br.bookmark.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="Participant")
public class Participant implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="id")
	private long id;
	
	@Column(name="role") @Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToOne(cascade=CascadeType.ALL) @JoinColumn(name="idCommunity")
	private Community community;
	
	@ManyToOne(cascade=CascadeType.ALL) @JoinColumn(name="idUser")
	private User user;
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	public Role getRole() {
		return role;
	}
	
	public void setCommunity(Community community) {
		this.community = community;
	}
	public Community getCommunity() {
		return community;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
}
