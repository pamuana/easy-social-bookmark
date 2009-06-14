package br.bookmark.action.community;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import com.opensymphony.xwork2.validator.annotations.Validation;

import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyCommunity", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/listMyCommunity-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/listMyCommunity-success.jsp")
})
@Validation
public class UnsubscribeCommunityAction extends BaseCommunityAction {

	private static final long serialVersionUID = 1L;
	
	public String execute() throws Exception{
		
		User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		String idUser = ""+user.getId();
		
		this.service.removeParticipant(this.idCommunity,idUser);
		return SUCCESS;
	}

}
