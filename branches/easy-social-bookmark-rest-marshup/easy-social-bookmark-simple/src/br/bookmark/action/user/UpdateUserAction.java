package br.bookmark.action.user;

import org.apache.struts2.config.Results;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import br.bookmark.models.User;

import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="index", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/user/findUser-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/user/findUser-success.jsp")
})
@Validation
public class UpdateUserAction extends BaseUserAction {

	private static final long serialVersionUID = 1L;

	@Validations( visitorFields = {
			@VisitorFieldValidator(
					message = "Default message", 
					fieldName= "model", appendPrefix = false) }
	)
	public String execute() throws Exception {
		String toReturn=SUCCESS;
		User userAux = service.findByField("login", user.getLogin());
		if (userAux!=null && user.getId()!=userAux.getId()) {
			addActionError(getText("The login is invalid this was register."));
			toReturn = INPUT; 
		}else {
			this.user = service.persist(this.user, this.idUser);
		}
		this.user = service.findById(idUser);
		return toReturn;
	}
	
}
