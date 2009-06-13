
package br.bookmark.action.bookmark;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import br.bookmark.models.Bookmark;


@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyBookmark", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/shareBookmark-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/shareBookmark-success.jsp")
})
public class ShareBookmarkAction extends BaseBookmarkAction{

	private static final long serialVersionUID = 1L;
	

	public String execute() throws Exception {
		this.bookmark.setShared("true");
		service.persist(this.bookmark, this.idBookmark);
		return SUCCESS;
	}
}
