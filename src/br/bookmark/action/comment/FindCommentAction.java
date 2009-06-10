
package br.bookmark.action.comment;

import org.apache.struts2.config.ParentPackage;

import br.bookmark.models.Bookmark;

@ParentPackage("base-package")
public class FindCommentAction extends BaseCommentAction {

	private static final long serialVersionUID = 1L;
	
	private String idBookmark;

    public String execute() throws Exception {
        return SUCCESS;
    }

	public void setIdBookmark(String idBookmark) {
		this.idBookmark = idBookmark;
	}

	public String getIdBookmark() {
		return idBookmark;
	}


}
