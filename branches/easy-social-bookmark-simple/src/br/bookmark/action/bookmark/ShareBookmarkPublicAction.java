
package br.bookmark.action.bookmark;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import br.bookmark.models.BookmarkPublic;
import br.bookmark.services.BookmarkPrivateService;


@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyBookmark", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/shareBookmarkPublic-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/shareBookmarkPublic-success.jsp")
})
public class ShareBookmarkPublicAction extends BaseBookmarkPublicAction{

	private static final long serialVersionUID = 1L;
	

	public String execute() throws Exception {
		bookmark = new BookmarkPublic();
		service.persist(this.bookmark, this.idBookmark);

		return SUCCESS;
	}
}
