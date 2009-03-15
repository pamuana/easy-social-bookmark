package br.bookmark.models;
import br.bookmark.db.DBField;
public class Tag {
	@DBField(type="Long") private long id;
    @DBField(type="Long") private long idUser;
    @DBField(type="Long") private long idCommunity;
    @DBField(type="Text(200)") private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdCommunity() {
		return idCommunity;
	}
	public void setIdCommunity(long idCommunity) {
		this.idCommunity = idCommunity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
