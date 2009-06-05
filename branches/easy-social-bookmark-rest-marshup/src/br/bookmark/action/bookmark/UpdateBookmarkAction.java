package br.bookmark.action.bookmark;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="index", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/findBookmark-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/findBookmark-success.jsp")
})
@Validation
public class UpdateBookmarkAction extends BaseBookmarkAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;		
	}

	@Validations( visitorFields = {
			@VisitorFieldValidator(
					message = "Default message", 
					fieldName= "model", appendPrefix = false) }
	)
	public String execute() throws Exception {
		
		service.persist(this.bookmark,this.idBookmark);

		User userADD =  (User) this.request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		if (userADD!=null){
			service.addUser(this.bookmark,""+userADD.getId());
		}

		return SUCCESS;
	}

	/*
	private HttpServletRequest request;

	public void prepare() throws Exception {
		User userADD =  (User) this.request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
        super.prepare();
        bookmark.setUser(userADD);
    }

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;		
	}
	 */
}
