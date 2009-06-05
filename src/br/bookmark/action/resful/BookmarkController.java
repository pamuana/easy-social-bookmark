package br.bookmark.action.resful;

import java.util.Collection;

/*
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
*/
import br.bookmark.models.Bookmark;
import br.bookmark.services.BookmarkService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

//@Results({
//	@Result(name="success", type="redirectAction", params = {"actionName" , "orders"})
//})
public class BookmarkController extends ValidationAwareSupport implements ModelDriven<Object>, Validateable{

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}

	/*
	private Bookmark model = new Bookmark();
	private String id;
	private Collection<Bookmark> list;
	private BookmarkService service;

	public void setBookmarkService(BookmarkService service) {
		this.service = service;
	}

	// GET /orders/1
	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// GET /orders
	public HttpHeaders index() {
		list = service.listAll();
		return new DefaultHttpHeaders("index").disableCaching();
	}

	// GET /orders/1/edit
	public String edit() {
		return "edit";
	}

	// GET /orders/new
	public String editNew() {
		model = new Bookmark();
		return "editNew";
	}

	// GET /orders/1/deleteConfirm
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// DELETE /orders/1
	public String destroy() {
		service.remove(id);
		addActionMessage("Order removed successfully");
		return "success";
	}

	// POST /orders
	public HttpHeaders create() {
		service.persist(model, id);
		addActionMessage("New order created successfully");
		return new DefaultHttpHeaders("success").setLocationId(model.getId());
	}

	// PUT /orders/1
	public String update() {
		service.persist(model, id);
		addActionMessage("Order updated successfully");
		return "success";
	}

	public void validate() {
		if (model.getName() == null || model.getName().length() ==0) {
			addFieldError("clientName", "The client name is empty");
		}
	}

	public void setId(String id) {
		if (id != null) {
			this.model = service.findById(id);
		}
		this.id = id;
	}

	public Object getModel() {
		return (list != null ? list : model);
	}
*/
}
