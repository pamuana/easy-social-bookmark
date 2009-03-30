/* Edita autor */
package br.bookmark.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.bookmark.project.*;
import br.bookmark.db.BookmarkDAO;
import br.bookmark.db.CommentDAO;
import br.bookmark.db.CommunityDAO;
import br.bookmark.models.*;


/**
 * Servlet implementation class AutorEdit
 */
public class BookmarkList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listBookmark(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listBookmark(request,response);		
	}

	private void listBookmark(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String url = null;		
		
/*		BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
		TagDAO tagDAO = bookmarkInit.getTagDAO();
		
		String idUser=session.getAttribute("idUser").toString();
		String idCommunity = request.getParameter("idCommunity").toString();
		String idTag = request.getParameter("idTag");
		
		CommunityDAO communityDAO= bookmarkInit.getCommunityDAO();
		CommentDAO commentDAO= bookmarkInit.getCommentDAO(); 
		
		if(request.getParameter("send")!=null){
			if (operation.equals("new")){
				comment.setText(request.getParameter("text"));
				((Comment) comment).setIdBookmark(Long.parseLong(idBookmark));
				comment.setIdUser(Long.parseLong(idUser));
			}
			
		try { 
			commentDAO.save(comment);
			url="bookmarkCommunityList.jsp?idCommunity="+bookmarkDAO.findById(Long.parseLong(idBookmark)).getIdCommunity();	   
 
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
	}*/
	}
	

}
