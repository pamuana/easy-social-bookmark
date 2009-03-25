package br.bookmark.models;

import br.bookmark.db.util.DBField;

public class Log {
	@DBField(type="Long") private long id;
	@DBField(type="Long") private long idUser;
	@DBField(type="Long") private long idBookmark;
	@DBField(type="Text(200)") private String url;
	@DBField(type="Long") private long date;
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdBookmark(long idBookmark) {
		this.idBookmark = idBookmark;
	}
	public long getIdBookmark() {
		return idBookmark;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public long getDate() {
		return date;
	}
	
}
