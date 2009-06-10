package br.bookmark.action.bookmark;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import br.bookmark.models.User;
import br.bookmark.services.BookmarkPrivateService;
import br.bookmark.services.UserService;
import br.bookmark.util.SecurityInterceptor;

import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyBookmark", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/findBookmarkPrivate-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/findBookmarkPrivate-success.jsp")
})
@Validation
public class UpdateBookmarkPrivateAction extends BaseBookmarkPrivateAction  {
	
	protected UserService userService;

	private static final long serialVersionUID = 1L;
	
	public void setUserService(UserService service) {
		this.userService = service;
	}
	
	@Validations( visitorFields = {
			@VisitorFieldValidator(
					message = "Default message", 
					fieldName= "model", appendPrefix = false) }
	)
	public String execute() throws Exception {


		User userADD =  (User) this.request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);

		service.persist(this.bookmark,this.idBookmark);
		this.bookmark.setUser(userService.findById(userADD.getId()+""));
		service.persist(this.bookmark,this.bookmark.getId()+"");

		return SUCCESS;
	}

}
