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
public class BookmarkNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			bookmarkNew(request,response);
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
			bookmarkNew(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void bookmarkNew(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String idUser = (String) session.getAttribute("idUser");
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
		
		Bookmark bookmark= new Bookmark();
		BookmarkDAO bookmarkDAO =bookmarkInit.getBookmarkDAO();
		TagDAO tagDAO = bookmarkInit.getTagDAO();
		bookmark.setIdUser(Long.parseLong(idUser));
		bookmark.setName(request.getParameter("name"));
		bookmark.setUrl(request.getParameter("url"));
		bookmark.setDescription(request.getParameter("description"));
		bookmarkDAO.save(bookmark);
		
		String[] nameTags = request.getParameter("tags").split(",");
		Collection<Bookmark> userBookmarks=bookmarkDAO.findBookmarksByIdUser(Long.parseLong(idUser));
		for (Bookmark userBookmark:userBookmarks){
			if (userBookmark.getName().equals(bookmark.getName())&&userBookmark.getUrl().equals(bookmark.getUrl())){
				tagDAO.assignBookmarkWithIdUser(nameTags,userBookmark.getId(),Long.parseLong(idUser));
			}
		}

		

		try { 
			bookmarkDAO.save(bookmark);    
			response.sendRedirect("bookmarkList.jsp"); 
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
