import commons.db.DBField;
public class Message {
	@DBField(type="Long") private long id;
    @DBField(type="Long") private long idUser;
    @DBField(type="Long") private long idCommunity;
    @DBField(type="Text(200)") private String text;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
