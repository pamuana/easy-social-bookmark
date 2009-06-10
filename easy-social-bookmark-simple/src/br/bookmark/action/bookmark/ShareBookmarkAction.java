
package br.bookmark.action.bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import br.bookmark.models.Participant;
import br.bookmark.models.User;
import br.bookmark.util.SecurityInterceptor;

@ParentPackage("base-package")
public class ShareBookmarkAction extends BaseBookmarkAction implements  ServletRequestAware {

	private static final long serialVersionUID = 1L;
	
	private List<Participant> participants = new ArrayList<Participant>();
	protected HttpServletRequest request;
	
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public String execute() throws Exception {
		this.participants = ( (User) this.request.getSession(true).getAttribute(SecurityInterceptor.USER_OBJECT)).getParticipants();
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request=httpServletRequest;
	}

}
