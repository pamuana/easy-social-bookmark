package br.bookmark.util;

import java.util.ArrayList;
import java.util.List;

import br.bookmark.util.tagcloud.FontSizeComputationStrategy;
import br.bookmark.util.tagcloud.TagCloud;
import br.bookmark.util.tagcloud.TagCloudElement;
import br.bookmark.util.tagcloud.impl.LinearFontSizeComputationStrategy;
import br.bookmark.util.tagcloud.impl.TagCloudImpl;
import br.bookmark.util.textanalysis.impl.SimpleMetaDataExtractor;

public class TagUtil {

	/* Remove Acentuacao */
	public static String tiraAcento (String passa){
		passa = passa.replaceAll("[ÂÀÁÄÃ]","A");
		passa = passa.replaceAll("[âãàáä]","a");
		passa = passa.replaceAll("[ÊÈÉË]","E");
		passa = passa.replaceAll("[êèéë]","e");
		passa = passa.replaceAll("ÎÍÌÏ","I");
		passa = passa.replaceAll("îíìï","i");
		passa = passa.replaceAll("[ÔÕÒÓÖ]","O");
		passa = passa.replaceAll("[ôõòóö]","o");
		passa = passa.replaceAll("[ÛÙÚÜ]","U");
		passa = passa.replaceAll("[ûúùü]","u");
		passa = passa.replaceAll("Ç","C");
		passa = passa.replaceAll("ç","c"); 
		passa = passa.replaceAll("[ýÿ]","y");
		passa = passa.replaceAll("Ý","Y");
		passa = passa.replaceAll("ñ","n");
		passa = passa.replaceAll("Ñ","N");
		passa = passa.replaceAll("['<>\\|/]","");
		return passa;
	}

	/* Remover caracteres que nao sao do alfabeto */
	public static String removeChars(String texto){
		texto = texto.replaceAll("([^a-zA-z])"," ");
		return texto;
	}


	public static String normalizacao(String texto) {
		texto = texto.toLowerCase();
		return texto;
	}

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
		
		string = tiraAcento(string);
		string = removeChars(string);
		string = normalizacao(string);

		SimpleMetaDataExtractor ex = new SimpleMetaDataExtractor();
		MetaDataVector mdv = ex.extractMetaData("", string);
		FontSizeComputationStrategy strategy =  new LinearFontSizeComputationStrategy(3,"");
		TagCloud tagCloud = new TagCloudImpl(mdv,strategy);
		for (TagCloudElement tagCloudElement: tagCloud.getTagCloudElements()) {
			toReturn.add(tagCloudElement.getTagText());
		}

		return toReturn;
	}

}
