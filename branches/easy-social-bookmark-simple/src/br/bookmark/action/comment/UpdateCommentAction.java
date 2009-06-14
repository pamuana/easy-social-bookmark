package br.bookmark.action.comment;

import javax.xml.crypto.Data;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import br.bookmark.models.Bookmark;
import br.bookmark.services.BookmarkService;

import com.opensymphony.xwork2.validator.annotations.Validation;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listBookmark", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/comment/findComment-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/comment/findComment-success.jsp")
})
@Validation
public class UpdateCommentAction extends BaseCommentAction {

	private static final long serialVersionUID = 1L;
	
	private String idBookmark;
	private BookmarkService bookmarkService;

	public void setIdBookmark(String idBookmark) {
		this.idBookmark = idBookmark;
	}

	public String getIdBookmark() {
		return idBookmark;
	}
	
	public void setBookmarkService(BookmarkService service) {
		this.bookmarkService = service;
	}
	
	public String execute() throws Exception {

		this.comment.setData((new java.util.Date()).toString());
		this.commentService.persist(this.comment, this.idComment);
		
		Bookmark bookmark = this.bookmarkService.findById(this.idBookmark);
		this.comment.setBookmark(bookmark);
		this.commentService.persist(this.comment, ""+this.comment.getId());
		
		return SUCCESS;
	}

}
