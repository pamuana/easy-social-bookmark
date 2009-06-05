package br.bookmark.action.resful;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Bookmark;
import br.bookmark.services.BookmarkService;

public class BookmarkAction extends BaseAction implements ModelDriven<Bookmark>, Preparable {

	private static final long serialVersionUID = 1L;

	private List<Bookmark> results;
	private BookmarkService service;

	private long id;
	private String type;
	private Bookmark bookmark = new Bookmark();

	public void setBookmarkService(BookmarkService service) {
		this.service = service;
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

	public Bookmark getModel() {
		return bookmark;
	}

	public List<Bookmark> getResults() {
		return results;
	}


	public void prepare() throws Exception {
		if(id!=0) {
			bookmark = service.findById(""+id);
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
		service.persist(bookmark, ""+id);
		return index();
	}

}
