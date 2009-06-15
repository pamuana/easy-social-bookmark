package br.bookmark.action.decisionTree;

import org.apache.struts2.config.ParentPackage;

import br.bookmark.action.BaseAction;
import br.bookmark.util.DecisionTreeImpl;
import br.usp.ime.collective.decisiontree.Attribute;
import br.usp.ime.collective.decisiontree.ItemSet;
import br.usp.ime.collective.decisiontree.model.DecisionTree;
import br.usp.ime.collective.decisiontree.util.ItemSetMySql;

@ParentPackage("base-package")
public class ProcessWebBookmarkAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private String sqlQueryDT;
	private String entropy;
	
	public void setSqlQueryDT(String sqlQueryDT) {
		this.sqlQueryDT = sqlQueryDT;
	}
	public String getSqlQueryDT() {
		return sqlQueryDT;
	}
	public void setEntropy(String entropy) {
		this.entropy = entropy;
	}
	public String getEntropy() {
		return entropy;
	}
	
	public String execute() throws Exception{
		String path = request.getRealPath("/");
		DecisionTreeImpl.build(path,this.sqlQueryDT,this.entropy);
		
		return SUCCESS;
	}

}
