package br.bookmark.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity @Table( name="BookmarkPublic")
@PrimaryKeyJoinColumn(name="idBookmark")
public class BookmarkPublic extends Bookmark{

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="comment",cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

}
