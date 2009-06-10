package br.bookmark.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

public class BookmarkPublic extends Bookmark{

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="comment",cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

}
