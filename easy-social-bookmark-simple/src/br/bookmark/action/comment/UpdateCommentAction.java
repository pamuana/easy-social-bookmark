package br.bookmark.action.comment;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.action.bookmark.BaseBookmarkPrivateAction;
import br.bookmark.models.Bookmark;
import br.bookmark.models.Comment;
import br.bookmark.models.User;
import br.bookmark.services.BookmarkService;
import br.bookmark.services.CommentService;
import br.bookmark.util.SecurityInterceptor;

import com.opensymphony.xwork2.validator.annotations.Validation;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="index", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/comment/findComment-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/comment/findComment-success.jsp")
})
@Validation
public class UpdateCommentAction extends BaseCommentAction { // implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private String idBookmark;

	private Bookmark bookmark;

	//private String text;
	//private HttpServletRequest request;

	//public void setServletRequest(HttpServletRequest httpServletRequest) {
	//	this.request=httpServletRequest;		
	//}
	
	public void prepare() throws Exception{
		super.prepare();
		if( this.idBookmark==null || "".equals(this.idBookmark) ) {
			this.bookmark = new Bookmark();
		} else {
			this.bookmark = this.bookmarkService.findById(this.idBookmark);
		}
	}
	
	

	public String execute() throws Exception {

		/*
		User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
		Comment comment= new Comment();
		comment.setText(text);
		comment.setUser(user);
		comment.setBookmark(bookmark);
		bookmark.addComment(comment);
		service.persist(bookmark, idBookmark);*/


		//if (idBookmark!=null && !"".equals(idBookmark)){
		//	service.persistNative(comment,idBookmark);
		//}else{
		//	service.persist(comment,idComment);
		//}
		
		
		//this.comment.setBookmark()
		//this.comment.setBookmark(this.bookmark);
		this.commentService.persist(this.comment, this.idComment);
		//this.commentService.
		
		//if ()
		//service.persistNative(this.comment, this.idBookmark);
		
		return SUCCESS;
	}

	public void setIdBookmark(String idBookmark) {
		this.idBookmark = idBookmark;
	}

	public String getIdBookmark() {
		return idBookmark;
	}

	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}

	public Bookmark getBookmark() {
		return bookmark;
	}

	/*
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	*/

}
