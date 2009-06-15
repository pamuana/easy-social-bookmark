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
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/listSubscribeCommunity-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/listSubscribeCommunity-success.jsp")
})
@Validation
public class SubscribeCommunityAction extends BaseCommunityAction {

	private static final long serialVersionUID = 1L;
	
	public String execute() throws Exception{
		
		User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		String idUser = ""+user.getId();
		
		this.service.addParticant(this.idCommunity,idUser);
		
		//..add list of community in session variable communityListText
		String communityListText = service.getCommunityListText(idUser,request.getContextPath()+"/bookmark/listCommunityBookmark.action?idCommunity=");
		request.getSession(true).setAttribute("communityListText",communityListText);
		
		return SUCCESS;
	}

}
