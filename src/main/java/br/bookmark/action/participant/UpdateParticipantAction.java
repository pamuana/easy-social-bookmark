package br.bookmark.action.participant;

import org.apache.struts2.config.Results;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

@ParentPackage("base-package")
@Results({
    @Result(name="success", value="index", type= ServletActionRedirectResult.class),
    @Result(name="dupPK",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/participant/findParticipant-success.jsp"),
    @Result(name="input",type= ServletDispatcherResult.class,value="/WEB-INF/jsp/participant/findParticipant-success.jsp")
})
@Validation
public class UpdateParticipantAction extends BaseParticipantAction {

	private static final long serialVersionUID = 1L;

	@Validations( visitorFields = {
            @VisitorFieldValidator(
                    message = "Default message", 
                    fieldName= "model", appendPrefix = false) }
    )
    public String execute() throws Exception {
        service.persist(participant,idParticipant);
        return SUCCESS;
    }
}
