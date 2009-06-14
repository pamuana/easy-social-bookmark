package br.bookmark.action.community;

import org.apache.struts2.config.ParentPackage;

@ParentPackage("base-package")
public class FindCommunityAction extends BaseCommunityAction{

	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		return SUCCESS;
	}
}
