import commons.db.DBField;
public class Community {
 	@DBField(type="Long") private long id;
    @DBField(type="Text(200)") private String name;
    @DBField(type="Long") private long idAdmin;
    @DBField(type="Long") private long idParent;
    @DBField(type="Text(200)") private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(long idAdmin) {
		this.idAdmin = idAdmin;
	}
	public long getIdParent() {
		return idParent;
	}
	public void setIdParent(long idParent) {
		this.idParent = idParent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
