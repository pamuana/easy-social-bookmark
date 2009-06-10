
package br.bookmark.action.user;

import org.apache.struts2.config.ParentPackage;


@ParentPackage("base-package")
public class FindUserAction extends BaseUserAction  // implements ServletRequestAware
{
	
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		return SUCCESS;
	}
}
