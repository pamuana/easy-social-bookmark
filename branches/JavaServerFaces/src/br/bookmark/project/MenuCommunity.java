package br.bookmark.project;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import br.bookmark.db.CommunityDAO;
import br.bookmark.models.Community;

/**
 * Widget for principal menu
 *
 */
public class MenuCommunity extends TagSupport {
    
    private CommunityDAO communityDAO;
    private String idUser;
    
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setCommunityDAO(CommunityDAO communityDAO) {
		this.communityDAO = communityDAO;
	}
	public CommunityDAO getCommunityDAO() {
		return communityDAO;
	}
	public int doStartTag() {
      try {
    	  JspWriter out = pageContext.getOut();
            
    	  Collection<Community> communities=communityDAO.findCommunitiesByIdUser(Long.parseLong(idUser));
    	  for (Community community:communities){
    		  if (community.getIdParent()==0){
    			  out.println(
    					  "<li><a href=\"../bookmark/bookmarkCommunityList.jsp?idCommunity="+community.getId()+"\">"+community.getName()+"</a>"+
    					  "<ul>"
    			  );
				Collection<Community> subcommunities=communityDAO.findByIdParent(community.getId());
				for (Community subcommunity:subcommunities){
					out.println(
					"<li><a href=\"../bookmark/bookmarkCommunityList.jsp?idCommunity="+subcommunity.getId()+"\">"+subcommunity.getName()+"</a></li>");
				}
				out.println("</ul>");
				out.println("</li>");
			}
		}          
         
            
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