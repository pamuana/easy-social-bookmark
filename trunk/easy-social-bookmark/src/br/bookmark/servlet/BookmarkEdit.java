/* Edita autor */
package br.bookmark.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.bookmark.project.*;
import br.bookmark.db.BookmarkDAO;
import br.bookmark.db.CommentDAO;
import br.bookmark.db.CommunityDAO;
import br.bookmark.db.TagDAO;
import br.bookmark.db.util.GenericDAO;
import br.bookmark.models.*;

/**
 * Servlet implementation class AutorEdit
 */
public class BookmarkEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			bookmarkEdit(request,response);
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
			bookmarkEdit(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void bookmarkEdit(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String idUser = (String) session.getAttribute("idUser");
		String url ="bookmarkList.jsp";		
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 

		BookmarkDAO bookmarkDAO =bookmarkInit.getBookmarkDAO();
		TagDAO tagDAO = bookmarkInit.getTagDAO();
		Bookmark bookmark=bookmarkDAO.findById(Long.parseLong(request.getParameter("idBookmark")));
		bookmark.setName(request.getParameter("name"));
		bookmark.setUrl(request.getParameter("url"));
		bookmark.setDescription(request.getParameter("description"));
		
		
		String[] nameTags = request.getParameter("tags").split(",");
		Collection<String> collectionNameTags=new ArrayList<String>();
		for (String editNameTag:nameTags){
			collectionNameTags.add(editNameTag);
		}
		
		Collection<Tag> currentTags=tagDAO.findTagsByIdBookmark(bookmark.getId());
		for (Tag currentTag:currentTags){
			if (!collectionNameTags.contains(currentTag.getName())){
				tagDAO.deassignBookmark(currentTag.getId(),bookmark.getId());
			}
		}
		
		tagDAO.assignBookmarkWithIdUser(nameTags,bookmark.getId(),Long.parseLong(idUser));

		

		try { 
			bookmarkDAO.save(bookmark);
			url="bookmarkList.jsp";	    
			response.sendRedirect(url); 
		} catch (ClassNotFoundException e) {   
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
