
package br.bookmark.action.community;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyCommunity", type= ServletActionRedirectResult.class)
})
public class DeleteCommunityAction extends BaseCommunityAction{

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		this.service.remove(this.idCommunity);
		
		//..add list of community in session variable communityListText
		String idUser = ""+((User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT)).getId();
		String communityListText = service.getCommunityListText(idUser,request.getContextPath()+"/bookmark/listCommunityBookmark.action?idCommunity=");
		request.getSession(true).setAttribute("communityListText",communityListText);
		
		return SUCCESS;
	}
}
