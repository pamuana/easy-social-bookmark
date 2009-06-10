package br.bookmark.action.community;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

import br.bookmark.action.community.BaseCommunityAction;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="bookmark/listMyBookmark", type= ServletActionRedirectResult.class)
})
public class AddBookmarkAction extends BaseCommunityAction{

	private static final long serialVersionUID = 1L;
	
	private String idBookmark; 

	public String execute() throws Exception {

		service.addBookmark(community,idBookmark);

		return SUCCESS;
	}

	public void setIdBookmark(String idBookmark) {
		this.idBookmark = idBookmark;
	}

	public String getIdBookmark() {
		return idBookmark;
	}

}
