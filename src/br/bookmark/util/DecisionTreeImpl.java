package br.bookmark.util;

import java.sql.SQLException;

import org.hibernate.cfg.AnnotationConfiguration;

import br.usp.ime.collective.decisiontree.Attribute;
import br.usp.ime.collective.decisiontree.ItemSet;
import br.usp.ime.collective.decisiontree.model.DecisionTree;
import br.usp.ime.collective.decisiontree.util.ItemSetMySql;

public class DecisionTreeImpl {

	private static DecisionTree dt = null;
	
	private DecisionTreeImpl() {
	}
	
	public static void build(String path,String sqlQueryDT, String entropy) throws ClassNotFoundException, SQLException {
		
		 AnnotationConfiguration cfg = new AnnotationConfiguration();
		 
		 String url = cfg.getProperty("hibernate.connection.url");
		 String user = cfg.getProperty("hibernate.connection.username");
		 String password = cfg.getProperty("hibernate.connection.password");
		 
		 String stringConnection = url+"?"+"user="+user+"&password="+password;
		
		ItemSetMySql itemMySqlSet = new ItemSetMySql(stringConnection,"");
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
		String sqlQueryDT = "SELECT u.age as age, u.city as city, u.state as state, u.country as country, c.name as name FROM User u,participant p,Community c Where u.id=p.idUser AND p.idCommunity=c.id";
		String entropy ="0.9";
		build(path,sqlQueryDT,entropy);
	} 

}
