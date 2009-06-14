package br.bookmark.action.community;

import org.apache.struts2.config.Results;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import com.opensymphony.xwork2.validator.annotations.Validation;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyCommunity", type= ServletActionRedirectResult.class),
	@Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/findCommunity-success.jsp"),
	@Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/findCommunity-success.jsp")
})
@Validation
public class UpdateCommunityAction extends BaseCommunityAction {

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception{
		
		this.service.persist(this.community, this.idCommunity);		
		return SUCCESS;
	}
}
