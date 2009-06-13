package br.bookmark.action.bookmark;

import java.util.List;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import br.bookmark.models.Tag;
import br.bookmark.models.User;
import br.bookmark.services.TagService;
import br.bookmark.services.TagUserService;
import br.bookmark.services.UserService;
import br.bookmark.util.SecurityInterceptor;
import br.bookmark.util.TagUtil;

import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyBookmark", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/findBookmarkPrivate-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/bookmark/findBookmarkPrivate-success.jsp")
})
@Validation
public class UpdateBookmarkAction extends BaseBookmarkAction  {
	
	private static final long serialVersionUID = 1L;
	
	protected UserService userService;
	protected TagService tagService;
	protected TagUserService tagUserService;
	
	public void setUserService(UserService service) {
		this.userService = service;
	}
	
	public void setTagService(TagService service) {
		this.tagService = service;
	}
	
	public void setTagUserService(TagUserService service) {
		this.tagUserService = service;
	}
	
	@Validations( visitorFields = {
			@VisitorFieldValidator(
					message = "Default message", 
					fieldName= "model", appendPrefix = false) }
	)
	public String execute() throws Exception {


		String idUser =  ((User) this.request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT)).getId() + "";
		User user = this.userService.findById(idUser);
		
		
		service.persist(this.bookmark,this.idBookmark);
		this.bookmark.setUser(user);
		service.persist(this.bookmark,this.bookmark.getId()+"");
		
		//..creamos o relacoamento de tags
		this.tagUserService.removeByBookmark(idUser,""+this.bookmark.getId());
		List<String> tags = TagUtil.stringToTags(this.tags);
		for (String tagName : tags) {
			Tag tag = this.tagService.findByField("name", tagName);
			if (tag==null) {
				tag = new Tag();
				tag.setName(tagName);
				this.tagService.persist(tag, "");
				tag = this.tagService.findById(tag.getId()+"");
			}
			
			this.tagUserService.persist(idUser,""+tag.getId(),""+this.bookmark.getId());
		}
		
		String cloudText= tagUserService.getCloud(""+user.getId(),request.getContextPath()+"/bookmark/listMyBookmark.action?tag=");
		request.getSession(true).setAttribute("cloudText",cloudText);
		
		return SUCCESS;
	}

}
