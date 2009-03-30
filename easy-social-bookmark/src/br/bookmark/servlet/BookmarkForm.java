/* Edita autor */
package br.bookmark.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.bookmark.project.*;
import br.bookmark.db.BookmarkDAO;
import br.bookmark.db.CommunityDAO;
import br.bookmark.db.TagDAO;
import br.bookmark.models.*;


/**
 * Servlet implementation class AutorEdit
 */
public class BookmarkForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			bookmarkForm(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			bookmarkForm(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void bookmarkForm(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String src = null;		
		
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	    
		BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
	    TagDAO tagDAO = bookmarkInit.getTagDAO();
	    CommunityDAO communityDAO = bookmarkInit.getCommunityDAO();
	    
	    String idUser = session.getAttribute("idUser").toString();
	    
	    String name="";
	    String url="";
	    String description="";
		String tagsString="";
		String operation="new"; 
	    if (request.getParameter("idBookmark")!=null){
	    	Bookmark bookmark=bookmarkDAO.findById(Long.parseLong(request.getParameter("idBookmark")));
	    	name=bookmark.getName();
	    	url=bookmark.getUrl();
	    	description=bookmark.getDescription();
	    	Collection<Tag> tags = tagDAO.findTagsByIdBookmark(Long.parseLong(request.getParameter("idBookmark")));
	    	for (Tag tag: tags){
	    		tagsString=tagsString+tag.getName()+",";
	    	}
	    	if (tagsString.length()>0){
	    		tagsString=tagsString.substring(0,tagsString.length()-1);
	    	}
			operation="edit";
	    }
	    if (request.getParameter("operation")!=null&&request.getParameter("operation").equals("share")){
	    	operation="share";
	    }
			
		try { 
			src="bookmarkForm.jsp?name="+name+"url="+url+"description="+description+"tagsString="+tagsString+"operation="+operation; 
			response.sendRedirect(url); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
