package br.bookmark.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TagUtil {

	public static String tagsToString(String[] tags) {
		String toReturn="";
		for(String tagName : tags){
			tagName=tagName.replaceAll(" ","");
			if (!"".equals(tagName) && !toReturn.contains(tagName)){
				toReturn += ","+tagName;
			}
		}
		return toReturn.substring(1);
	}
	
	public static List<String> stringToTags(String string) {
		List<String> toReturn = new ArrayList<String>();
		Pattern p = Pattern.compile(",+|\n+| +");
                String[] tags =p.split(string);
                
                for(String tagName : tags){
			tagName=tagName.replaceAll(" ","");
			tagName=tagName.replaceAll("\n","");
			if (!"".equals(tagName) && !toReturn.contains(tagName)){
				toReturn.add(tagName);
			}
		}
                
                return toReturn;
	}

}
