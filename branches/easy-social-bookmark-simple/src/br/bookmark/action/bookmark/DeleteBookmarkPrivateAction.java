
package br.bookmark.action.bookmark;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyBookmark", type= ServletActionRedirectResult.class)
})
public class DeleteBookmarkPrivateAction extends BaseBookmarkPrivateAction{

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		this.getService().remove(""+this.getBookmark().getId());
		return SUCCESS;
	}
}
