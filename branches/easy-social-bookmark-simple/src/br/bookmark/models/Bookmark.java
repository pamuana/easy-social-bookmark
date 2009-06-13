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
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name="Bookmark") //@Inheritance(strategy=InheritanceType.JOINED)
public class Bookmark implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;

	@Column(name="url")
	private String url;

	@Column(name = "name")
	private String name;

	@Column(name="description")
	private String description;

	@Column(name="shared")
	private String shared;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idUser")
	private User user;

	@OneToMany(mappedBy="bookmark",cascade=CascadeType.ALL)
	private List<TagUser> tagsUser = new ArrayList<TagUser>();

	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}

	@RequiredStringValidator(message="the field url is requiered")
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}

	@RequiredStringValidator(message="the field name is requiered")
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

	public void setShared(String shared) {
		this.shared = shared;
	}
	public String getShared() {
		return shared;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}

	public void setTagsUser(List<TagUser> tagsUser) {
		this.tagsUser = tagsUser;
	}
	public List<TagUser> getTagsUser() {
		return tagsUser;
	}
	public void addTagUser(TagUser tagUser) {
		tagsUser.add(tagUser);
	}

}
