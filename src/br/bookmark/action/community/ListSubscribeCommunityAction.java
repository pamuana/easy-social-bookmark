package br.bookmark.action.community;

import java.util.List;

import org.apache.struts2.config.ParentPackage;

import br.bookmark.models.Community;
import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ListSubscribeCommunityAction extends BaseCommunityAction {

	private static final long serialVersionUID = 1L;

	private List<Community> communities;

	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	public String execute() throws Exception {
		
		User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		String idUser = ""+user.getId();
		
		this.communities = this.service.listCommunityWithOutSubscrition(idUser);
		
		return SUCCESS;
	}

}
