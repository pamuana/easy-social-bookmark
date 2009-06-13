
package br.bookmark.action.bookmark;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

import br.bookmark.models.User;
import br.bookmark.services.TagUserService;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyBookmark", type= ServletActionRedirectResult.class)
})
public class DeleteBookmarkAction extends BaseBookmarkAction{

	private static final long serialVersionUID = 1L;
	
	private TagUserService tagUserService;
	
	public void setTagUserService(TagUserService service) {
		this.tagUserService = service;
	}

	public String execute() throws Exception {
		this.service.remove(""+this.bookmark.getId());
		String idUser =  ((User) this.request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT)).getId() + "";
		this.tagUserService.removeByBookmark(idUser, idBookmark);
		
		String cloudText= tagUserService.getCloud(idUser,request.getContextPath()+"/bookmark/listMyBookmark.action?tag=");
		request.getSession(true).setAttribute("cloudText",cloudText);
		
		return SUCCESS;
	}
}
