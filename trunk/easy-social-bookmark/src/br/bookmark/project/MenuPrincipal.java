package br.bookmark.project;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Widget for principal menu
 *
 */
public class MenuPrincipal extends TagSupport {
    
    public int doStartTag() {
      try {

            JspWriter out = pageContext.getOut();
            String context = (((HttpServletRequest) pageContext.getRequest()).getContextPath());
            out.println("á -->"+context);
            out.println(
            		"<h2>Main Menu</h2>" +
        			"<div class=\"content\">" +
						"<ul>" +
  							"<li><a title=\"view bookmark\" href=\"../bookmark/bookmarkList.jsp\">&raquo;&nbsp;View Bookmark</a></li>" +
  							"<li><a title=\"new bookmark\" href=\"../bookmark/bookmarkForm.jsp\">&raquo;&nbsp;New Bookmark</a></li>" +
  							"<li><a title=\"view interesting\" href=\"#\">&raquo;&nbsp;View Interesting</a></li>" +
  							"<li><a title=\"view statistic\" href=\"#\">&raquo;&nbsp;View Statistics</a></li>" +
						"</ul>" +
						"<br/><p/>&nbsp;" +
						"<ul>" +
        					"<li><a title=\"list communities\" href=\"../community/communityList.jsp\">&raquo;&nbsp;List Communities</a></li>" +
        					"<li><a title=\"new community\" href=\"../community/communityForm.jsp?create=create\">&raquo;&nbsp;New Community</a></li>" +
        					"<li><a title=\"add community\" href=\"../community/communityForm.jsp?addcommunity=addcommunity\">&raquo;&nbsp;Add Community</a></li>" +
    					"</ul>" +    			
        			"</div>"
            		);
            
      } catch (Exception ex) {
          ex.printStackTrace();
          throw new Error("Error "+ex.getMessage());
      }
      // Must return SKIP_BODY because we are not supporting a body for this 
      // tag.
      return SKIP_BODY;
    }
/**
 * doEndTag is called by the JSP container when the tag is closed
 */
    public int doEndTag(){
       try {
           JspWriter out = pageContext.getOut();
           return SKIP_BODY;
       } catch (Exception ex){
           throw new Error("Error");
       }
    }


}