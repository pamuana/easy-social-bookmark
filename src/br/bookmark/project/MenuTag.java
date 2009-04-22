package br.bookmark.project;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import br.bookmark.db.TagDAO;
import br.bookmark.models.Tag;

/**
 * Widget for principal menu
 *
 */
public class MenuTag extends TagSupport {
	
	private TagDAO tagDAO;
	private String idUser;
	private String idCommunity;
    
    public void setIdCommunity(String idCommunity) {
		this.idCommunity = idCommunity;
	}
	public String getIdCommunity() {
		return idCommunity;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}
	public TagDAO getTagDAO() {
		return tagDAO;
	}
	public int doStartTag() {
      try {

            JspWriter out = pageContext.getOut();
            
			Collection<Tag> tags=getTagDAO().findTagsByIdUser(Long.parseLong(getIdUser()));
			
			if (getIdCommunity()!=null&&!"".equals(getIdCommunity())){
				tags=tagDAO.findTagsByIdCommunity(Long.parseLong(getIdCommunity()));
			}

			for (Tag tag:tags){
				out.println("<a href=\"../bookmark/bookmarkList.jsp?idTag="+tag.getId()+"\">"+tag.getName()+"</a>&nbsp;&nbsp;");
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
