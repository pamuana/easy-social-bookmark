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

@Entity
@Table(name="Tag")
public class Tag  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="tag",cascade=CascadeType.ALL)
	private List<TagUser> tagsUser = new ArrayList<TagUser>();

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
