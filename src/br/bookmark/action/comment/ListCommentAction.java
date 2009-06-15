
package br.bookmark.action.comment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.action.BaseAction;
import br.bookmark.models.Comment;
import br.bookmark.models.User;
import br.bookmark.services.CommentService;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ListCommentAction extends BaseAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;

	protected List<Comment> comments = new ArrayList<Comment>();
	protected CommentService service;
	protected HttpServletRequest request;


	public void setCommentService(CommentService service) {
		this.service = service;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;		
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public String execute() throws Exception{
		//String idUser = ""+((User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT)).getId();
		//this.comments = service.listByField("idBookmark", idBookmark);
		return SUCCESS;
	}

}
