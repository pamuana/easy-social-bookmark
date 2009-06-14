package br.bookmark.action.community;

import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

public class SubscribeCommunityAction extends BaseCommunityAction {

	private static final long serialVersionUID = 1L;
	
	public String execute() throws Exception{
		
		User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		String idUser = ""+user.getId();
		
		this.service.addParticant(this.idCommunity,idUser);
		return SUCCESS;
	}

}
