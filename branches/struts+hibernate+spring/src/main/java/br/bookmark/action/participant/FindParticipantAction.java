
package br.bookmark.action.participant;

import org.apache.struts2.config.ParentPackage;

@ParentPackage("base-package")
public class FindParticipantAction extends BaseParticipantAction
//        implements ServletRequestAware
{

//    private HttpServletRequest request;
//
//    public void setServletRequest(HttpServletRequest httpServletRequest) {
//        request = httpServletRequest;
//    }

	private static final long serialVersionUID = 1L;

	//    @Validations( visitorFields = {
//        @VisitorFieldValidator(fieldName="user", message="Default message", key="i18n.key", shortCircuit=true, appendPrefix=false) }
//    )
    public String execute() throws Exception {
//        if( user!=null ) {
//            request.getSession(true).setAttribute("user",user);
//        }
        return SUCCESS;
    }
}
