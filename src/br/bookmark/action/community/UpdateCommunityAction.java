package br.bookmark.action.community;

import org.apache.struts2.config.Results;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="index", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/findCommunity-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/findCommunity-success.jsp")
})
@Validation
public class UpdateCommunityAction extends BaseCommunityAction {

	private static final long serialVersionUID = 1L;

	private String idUser;

	@Validations( visitorFields = {
			@VisitorFieldValidator(
					message = "Default message", 
					fieldName= "model", appendPrefix = false) }
	)
	public String execute() throws Exception {
		if (idUser!=null && !"".equals(idUser)){
			service.persist(community,idCommunity, idUser);
		}else{
			service.persist(community,idCommunity);
		}
		return SUCCESS;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdUser() {
		return idUser;
	}
}
