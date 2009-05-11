package br.bookmark.project;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import br.bookmark.db.BookmarkDAO;
import br.bookmark.db.CommentDAO;
import br.bookmark.db.CommunityDAO;
import br.bookmark.db.TagDAO;
import br.bookmark.models.Bookmark;
import br.bookmark.models.Comment;
import br.bookmark.models.Community;
import br.bookmark.models.Tag;

/**
 * Widget for principal menu
 *
 */
public class BookmarkList extends TagSupport {
    
	private String idUser;
	private TagDAO tagDAO;
	private BookmarkDAO bookmarkDAO;
	private CommunityDAO communityDAO;
	private CommentDAO commentDAO;
	private String idTag=null;
	private String idCommunity=null;
	
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
	public void setBookmarkDAO(BookmarkDAO bookmarkDAO) {
		this.bookmarkDAO = bookmarkDAO;
	}
	public BookmarkDAO getBookmarkDAO() {
		return bookmarkDAO;
	}
	public void setCommunityDAO(CommunityDAO communityDAO) {
		this.communityDAO = communityDAO;
	}
	public CommunityDAO getCommunityDAO() {
		return communityDAO;
	}
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}
	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}
	public String getIdTag() {
		return idTag;
	}
	public void setIdCommunity(String idCommunity) {
		this.idCommunity = idCommunity;
	}
	public String getIdCommunity() {
		return idCommunity;
	}
	
    public int doStartTag() {

    	JspWriter out = pageContext.getOut();
    	String context = (((HttpServletRequest) pageContext.getRequest()).getContextPath());
    	
    	if ((this.idCommunity!=null)&&(!"".equals(this.idCommunity))){
    		try {
        		printByCommunity(out);
        	} catch (Throwable ex) {
        		throw new Error("Error "+ex.getMessage());
    		}	
    	} else {
	    	try {
	    		printByUser(out);
	    	} catch (Throwable ex) {
	    		throw new Error("Error "+ex.getMessage());
			}
    	}
    	// Must return SKIP_BODY because we are not supporting a body for this 
    	// tag.
    	return SKIP_BODY;
    }
    
    private void printByUser(JspWriter out) throws Throwable{
    	Collection<String> bookmarkIds=new ArrayList<String>();
		if (this.idTag!=null){
			bookmarkIds=this.tagDAO.findIdBookmarksByIdTag(Long.parseLong(this.idTag));
		}
		
		Collection<Bookmark> bookmarks = this.bookmarkDAO.findBookmarksByIdUser(Long.parseLong(this.idUser));
		for (Bookmark bm : bookmarks) {
			boolean view=true;
			if (this.idTag!=null){
				view=bookmarkIds.contains(""+bm.getId());
			}
			
			if ((bm.getIdCommunity()==0)&&(view)){
				out.println(
        		"<div class=\"node\">" +
        		    "<h2 class=\"nodeTitle\"><a href=\""+bm.getUrl()+"\" target=\"_blank\">"+bm.getName()+" &nbsp;&nbsp; ("+bookmarkDAO.findByUrl(bm.getUrl()).size()+")</a></h2>" +
        		    "<div class=\"post\">" +
        		    "<div class=\"taxonomy\">" +
        				"Tag's:");
				Collection<Tag> tags=tagDAO.findTagsByIdBookmark(bm.getId());
				for (Tag tag : tags) {
					out.println(tag.getName());
				}
				out.println(
					"</div>" + 
        		    "<div class=\"shared\"></div>" +
        		    "<div class=\"url\"><a href=\""+bm.getUrl()+"\" target=\"_blank\">"+bm.getUrl()+"</div>" +
        		    "<div>" +
        		    "<a class=\"addcomment\" href=\"bookmarkForm.jsp?operation=share&idBookmark="+bm.getId()+"\">share</a>"+
        			"<a class=\"editlinks\" href=\"bookmarkForm.jsp?idBookmark="+bm.getId()+"\">edit</a>" +
        		    "<a href=\"BookmarkDelete?idBookmark="+bm.getId()+"\">delete</a>"+
        			"</div>"+
        			"</div>"+
        		    "</div>"+
        		    "<p/>&nbsp;"+
        		    "<hr/>"+
        		    "<p/>&nbsp;");
        	}
        }
    }
    
    private void printByCommunity(JspWriter out) throws Throwable{
    	
    	Community currentCommunity=this.communityDAO.findById(Long.parseLong(idCommunity));
    	
    	Collection<String> bookmarkIds=new ArrayList<String>();
    	if (this.idTag!=null){
    		bookmarkIds=tagDAO.findIdBookmarksByIdTag(Long.parseLong(this.idTag));
    	}

    	Collection<Bookmark> bookmarks = bookmarkDAO.findBookmarksByIdCommunity(Long.parseLong(idCommunity));
    	for(Bookmark bk : bookmarks){
    		boolean view=true;
    		if (this.idTag!=null){
    			view=bookmarkIds.contains(""+bk.getId());
    		}
    		if (view){
    			
    			out.print(
    				"<div class=\"node\">"+
    				"<h2 class=\"nodeTitle\"><a href=\""+bk.getUrl()+"\" target=\"_blank\">"+bk.getName()+"&nbsp;&nbsp; ("+bookmarkDAO.findByUrl(bk.getUrl()).size()+")</a></h2>"+
    				"<div class=\"post\">"+
    				"<div class=\"taxonomy\">"+
    				"Tag's:"
    			);
    			
    			for (Tag tag : tagDAO.findTagsByIdBookmark(bk.getId())) {
            		out.print(tag.getName()+", &nbsp;&nbsp;&nbsp;");
    			}
    			
    			out.print(
    				"</div>"+
    				"<div class=\"url\"><a href=\""+bk.getUrl()+"\" target=\"_blank\">"+bk.getUrl()+"</div>"+				
    				"<div class=\"content\">"+bk.getDescription()+"</div>"+
    					"<div>"+
    					"<a class=\"addcomment\" href=\"comments.jsp?idBookmark="+bk.getId()+"\">Add comment</a>"+
    					"<a class=\"editlinks\" href=\"bookmarkForm.jsp?idBookmark="+bk.getId()+"\">edit</a>,"
    			);
    			
    			if(currentCommunity.getIdAdmin() == Long.parseLong(idUser)){
    				out.print(";&nbsp;&nbsp;<a href=\"BookmarkDelete?idBookmark="+bk.getId()+"\">delete</a>");
            	}
    			
    			out.print(
    				"</div>"+
    				"<div class=\"comments\">");
				Collection<Comment> comments = this.commentDAO.findCommentsByIdBookmark(bk.getId());
    			for (Comment comment : comments) {
    				out.print("<div class=\"comment\">"+comment.getText()+"</div>");
    			}
            	
    			out.print(
    				"</div>"+
    				"</div>"+
    				"</div>"+
    				"<p/>&nbsp;"+
    				"<hr/>"+
    				"<p/>&nbsp;");
    		}
    	}
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

