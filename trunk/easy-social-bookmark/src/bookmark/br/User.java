package bookmark.br;
import commons.db.DBField;
public class User {
	@DBField(type="Long") private long id;
    @DBField(type="Text(200)") private String name;
    @DBField(type="Text(200)") private String login;
    @DBField(type="Text(200)") private String password;
    @DBField(type="Text(200)") private String email;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
