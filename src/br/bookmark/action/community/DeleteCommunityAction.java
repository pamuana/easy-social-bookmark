
package br.bookmark.action.community;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

@ParentPackage("base-package")
@Results({
	@Result(name="success", value="listMyCommunity", type= ServletActionRedirectResult.class)
})
public class DeleteCommunityAction extends BaseCommunityAction{

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		this.service.remove(Long.parseLong(idCommunity));
		return SUCCESS;
	}
}
