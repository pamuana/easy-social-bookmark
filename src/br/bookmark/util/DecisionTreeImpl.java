package br.bookmark.util;

import java.sql.SQLException;

import br.usp.ime.collective.decisiontree.Attribute;
import br.usp.ime.collective.decisiontree.ItemSet;
import br.usp.ime.collective.decisiontree.model.DecisionTree;
import br.usp.ime.collective.decisiontree.util.ItemSetMySql;

public class DecisionTreeImpl {

	private static DecisionTree dt = null;
	
	private DecisionTreeImpl() {
	}
	
	public static void build(String path,String sqlQueryDT, String entropy) throws ClassNotFoundException, SQLException {
		
		ItemSetMySql itemMySqlSet = new ItemSetMySql("jdbc:mysql://localhost/bookmarks?user=root&password=","");
		ItemSet itemSet = itemMySqlSet.read(sqlQueryDT);
		Attribute attributeObjetive = itemSet.getAttributeSet().lastElement();
		
		DecisionTree decisionTree = new DecisionTree(itemSet,attributeObjetive);
		
		decisionTree.toGIF(path+"original.gif");
		decisionTree.prune(Double.parseDouble(entropy));
		decisionTree.toGIF(path+"prune.gif");
		
		dt = decisionTree;
	}
	
	public static DecisionTree getInstance() throws ClassNotFoundException, SQLException {
		if (dt==null) {
			build();
		}
		return dt;
	}

	private static void build() throws ClassNotFoundException, SQLException {
		String path = "/";
		String sqlQueryDT = "SELECT u.email as email, c.name as name FROM User u,participant p,Community c Where u.id=p.idUser AND p.idCommunity=c.id";
		String entropy ="0.9";
		build(path,sqlQueryDT,entropy);
	} 

}
