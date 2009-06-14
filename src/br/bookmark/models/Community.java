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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Community")
public class Community implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="description")
	private String Description;
	
	@ManyToMany(cascade = { CascadeType.ALL})
	@JoinTable(name="participant",
			joinColumns={@JoinColumn(name="idCommunity")},
			inverseJoinColumns={@JoinColumn(name="idUser")})
	private List<User> users = new ArrayList<User>();
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	public void setName(String name) {
		Name = name;
	}
	public String getName() {
		return Name;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	public String getDescription() {
		return Description;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<User> getUsers() {
		return users;
	}
	public void addUser(User user) {
		this.users.add(user);
	}

}
