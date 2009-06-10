package br.bookmark.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name="Bookmark")
@Inheritance(strategy=InheritanceType.JOINED)
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

	@Column(name="tags")
	private String tags;
	
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

	@RequiredStringValidator(message="the field tag's is requiered")
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTags() {
		return tags;
	}

}
