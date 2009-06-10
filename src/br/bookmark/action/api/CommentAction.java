package br.bookmark.action.api;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Comment;
import br.bookmark.services.CommentService;

public class CommentAction extends BaseAction implements ModelDriven<Comment>, Preparable {

	private static final long serialVersionUID = 1L;

	private List<Comment> results;
	private CommentService service;

	private long id;
	private long idBookmark;
	private long idUser;
	private String type;
	private Comment comment = new Comment();

	public void setCommentService(CommentService service) {
		this.service = service;
	}
	
	public long getIdBookmark() {
		return idBookmark;
	}

	public void setIdBookmark(long idBookmark) {
		this.idBookmark = idBookmark;
	}
	
	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public Comment getModel() {
		return comment;
	}

	public List<Comment> getResults() {
		return results;
	}


	public void prepare() throws Exception {
		if(id!=0) {
			comment = service.findById(""+id);
		}
	}

	public String view() {
		if (type!=null && "xml".equals(type)) return "xml";
		return "single";
	}

	public String index() {
		results = service.listAll();
		return "list";
	}

	public String remove(){
		service.remove(""+id);
		return index();
	}

	public String editNew(){
		results = service.listAll();
		return "list";
	}

	public String create(){
		service.persist(comment, ""+idBookmark);
		return index();
	}

}
