package br.bookmark.action.bookmark;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

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

	private static final long serialVersionUID = 1L;
	
	@Validations( visitorFields = {
			@VisitorFieldValidator(
					message = "Default message", 
					fieldName= "model", appendPrefix = false) }
	)
	public String execute() throws Exception {
		
		//System.out.println(this.bookmark.getUser().getName());

		//User userADD =  (User) this.request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		//this.bookmark.setUser(userADD);
		//this.bookmark.setUser(userLogon);
		//System.out.println(this.bookmark.getUser().getName());
		service.persist(this.bookmark,this.idBookmark);
		//if (userADD!=null){
		service.addUser(this.bookmark,""+userLogon.getId());
		//}

		return SUCCESS;
	}

}
