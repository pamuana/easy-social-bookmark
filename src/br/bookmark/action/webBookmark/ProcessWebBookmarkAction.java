
package br.bookmark.action.webBookmark;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import br.bookmark.action.BaseAction;
import br.bookmark.services.WebBookmarkService;


@ParentPackage("base-package")
@Results({
	@Result(name="success", value="adminWebBookmark", type= ServletActionRedirectResult.class)
})
public class ProcessWebBookmarkAction extends BaseAction  {
	
	private static final long serialVersionUID = 1L;
	
	private String maxNumberUrls;
	private String  maxDepth;
	private String delayBetweenUrls;
	
	private WebBookmarkService webBookmarkService;
	
	public void setWebBookmarkService(WebBookmarkService service) {
		this.webBookmarkService= service;
	}

	public void setMaxNumberUrls(String maxNumberUrls) {
		this.maxNumberUrls = maxNumberUrls;
	}

	public String getMaxNumberUrls() {
		return maxNumberUrls;
	}

	public void setMaxDepth(String maxDepth) {
		this.maxDepth = maxDepth;
	}

	public String getMaxDepth() {
		return maxDepth;
	}

	public void setDelayBetweenUrls(String delayBetweenUrls) {
		this.delayBetweenUrls = delayBetweenUrls;
	}

	public String getDelayBetweenUrls() {
		return delayBetweenUrls;
	}
	
	public String execute() throws Exception {
		System.out.println(this.maxNumberUrls+"-"+this.maxDepth+"-"+this.delayBetweenUrls);
		this.webBookmarkService.crawlerProcess(this.maxNumberUrls,this.maxDepth,this.delayBetweenUrls);
		return SUCCESS;
	}
	
}
