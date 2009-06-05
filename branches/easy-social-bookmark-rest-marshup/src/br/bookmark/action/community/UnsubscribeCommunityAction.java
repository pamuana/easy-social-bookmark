package br.bookmark.action.community;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
@Results({
    @Result(name="success", value="index", type= ServletActionRedirectResult.class),
    @Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/listMyCommunity-success.jsp"),
    @Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/community/listMyCommunity.jsp")
})
public class UnsubscribeCommunityAction extends BaseCommunityAction implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	
    private HttpServletRequest request;
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

	

	//    @Validations( visitorFields = {
//        @VisitorFieldValidator(fieldName="user", message="Default message", key="i18n.key", shortCircuit=true, appendPrefix=false) }
//    )
    public String execute() throws Exception {
    	User user =  (User) request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT);
    	service.unsubscribeUser(community,""+user.getId());
        return SUCCESS;
    }
}
