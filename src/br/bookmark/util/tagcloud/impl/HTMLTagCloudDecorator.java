package br.bookmark.util.tagcloud.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.bookmark.util.tagcloud.TagCloud;
import br.bookmark.util.tagcloud.TagCloudElement;
import br.bookmark.util.tagcloud.VisualizeTagCloudDecorator;


public class HTMLTagCloudDecorator implements VisualizeTagCloudDecorator {
    private static final String HEADER_HTML =
        "<html><br><head><br><title>TagCloud <br></title><br></head>";
    private static final int NUM_TAGS_IN_LINE = 5;
    private Map<String, String> fontMap = null;
    
    public HTMLTagCloudDecorator() {
        getFontMap();
    }
    
    private void getFontMap() {
        //For your application, read mapping from xml file
        this.fontMap = new HashMap<String,String>();
        fontMap.put("font-size: 0", "font-size: 13px");
        fontMap.put("font-size: 1", "font-size: 20px");
        fontMap.put("font-size: 2", "font-size: 24px");
    }
    
    public String decorateTagCloud(TagCloud tagCloud,String href,String head) {
        StringWriter sw = new StringWriter();
        List<TagCloudElement> elements = tagCloud.getTagCloudElements();
        //sw.append(HEADER_HTML);
        //sw.append("<br><body><h3>TagCloud (" +  elements.size() +")</h3>");
        sw.append(head);
        int count = 0;
        for (TagCloudElement tce :  elements) {
            sw.append("&nbsp;<a href=\""+href+tce.getTagText()+"\" style=\""+ this.fontMap.get(tce.getFontSize())+";\">" );
            sw.append(tce.getTagText() +"</a>&nbsp;");
            if (count++ == NUM_TAGS_IN_LINE) {
                count = 0;
                sw.append("<br>" );
            }
        }
        //sw.append("<br></body><br></html>");
        System.out.println(sw.toString());
        return sw.toString();           
    }

}
