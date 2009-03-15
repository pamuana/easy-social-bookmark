package br.bookmark.models;
import br.bookmark.db.DBField;

public class Bookmark {

	 	@DBField(type="Long") private long id;
	    @DBField(type="Text(200)") private String url;
	    @DBField(type="Long") private long idUser;
	    @DBField(type="Text(200)") private String name;
	    @DBField(type="Long") private long idCommunity;
	    @DBField(type="Text(200)") private String description;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public long getIdUser() {
			return idUser;
		}
		public void setIdUser(long idUser) {
			this.idUser = idUser;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getIdCommunity() {
			return idCommunity;
		}
		public void setIdCommunity(long idCommunity) {
			this.idCommunity = idCommunity;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	    
}
