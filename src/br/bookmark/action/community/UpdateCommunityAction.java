package br.bookmark.action.community;

import org.apache.struts2.config.Results;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

import com.opensymphony.xwork2.validator.annotations.Validation;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyCommunity", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/findCommunity-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/findCommunity-success.jsp")
})
@Validation
public class UpdateCommunityAction extends BaseCommunityAction {

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception{
		
		this.service.persist(this.community, this.idCommunity);
		
		//..add list of community in session variable communityListText
		String idUser = ""+((User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT)).getId();
		String communityListText = service.getCommunityListText(idUser,request.getContextPath()+"/bookmark/listCommunityBookmark.action?idCommunity=");
		request.getSession(true).setAttribute("communityListText",communityListText);
		
		return SUCCESS;
	}
}
