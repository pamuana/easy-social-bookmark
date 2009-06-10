
package br.bookmark.action.bookmark;

import org.apache.struts2.config.ParentPackage;

@ParentPackage("base-package")
public class FindBookmarkPrivateAction extends BaseBookmarkPrivateAction{

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		return SUCCESS;
	}
}
