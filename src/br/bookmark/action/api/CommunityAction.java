package br.bookmark.action.api;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Community;
import br.bookmark.services.CommunityService;

public class CommunityAction extends BaseAction implements ModelDriven<Community>, Preparable {

	private static final long serialVersionUID = 1L;

	private List<Community> results;
	private CommunityService service;

	private long id;
	private long idUser;
	private String type;
	private Community community = new Community();

	public void setCommunityService(CommunityService service) {
		this.service = service;
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

	public Community getModel() {
		return community;
	}

	public List<Community> getResults() {
		return results;
	}


	public void prepare() throws Exception {
		if(id!=0) {
			community = service.findById(""+id);
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
		service.persist(community, ""+id, ""+idUser);
		return index();
	}

}
