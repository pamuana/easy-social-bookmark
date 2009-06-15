
package br.bookmark.action.webBookmark;

import org.apache.struts2.config.ParentPackage;

import br.bookmark.action.BaseAction;


@ParentPackage("base-package")
public class AdminWebBookmarkAction extends BaseAction  {
	
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		return SUCCESS;
	}
}
