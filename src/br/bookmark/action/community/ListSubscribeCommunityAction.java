package br.bookmark.action.community;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.models.Community;
import br.bookmark.models.Participant;
import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ListSubscribeCommunityAction extends BaseCommunityAction implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private List<Community> communities;
	private HttpServletRequest request;
	
	
	public void setCommunities(List<Community> communities) {
		this.communities = communities;
	}

	public List<Community> getCommunities() {
		return communities;
	}

	//    @Validations( visitorFields = {
//        @VisitorFieldValidator(fieldName="user", message="Default message", key="i18n.key", shortCircuit=true, appendPrefix=false) }
//    )
    public String execute() throws Exception {
    	this.communities=service.listAll();
    	User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
    	
    	this.communities = this.service.listCommunityWithOutSubscrition(""+user.getId());
//        if( user!=null ) {
//            request.getSession(true).setAttribute("user",user);
//        }
        return SUCCESS;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;		
	}


}
