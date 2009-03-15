package br.bookmark.models;

import br.bookmark.db.DBField;

public class Comment {
	@DBField(type="Long") private long id;
	@DBField(type="Text(500)") private String text;
	@DBField(type="Long") private long idBookmark;
	@DBField(type="Long") private long idUser;
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setIdBookmark(long idBookmark) {
		this.idBookmark = idBookmark;
	}
	public long getIdBookmark() {
		return idBookmark;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdUser() {
		return idUser;
	}
}
