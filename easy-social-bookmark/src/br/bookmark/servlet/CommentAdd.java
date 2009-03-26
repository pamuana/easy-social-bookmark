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
import java.util.*;

/**
 * Servlet implementation class AutorEdit
 */
public class CommentAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addComment(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addComment(request,response);		
	}

	private void addComment(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String url = null;		
		
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");

		CommentDAO commentDAO = bookmarkInit.getCommentDAO();
		
		BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
		
		String idBookmark = request.getParameter("idBookmark");
		String idUser = session.getAttribute("idUser").toString();
		String operation = ""+request.getParameter("operation");
		CommunityDAO CommunityDAO = bookmarkInit.getCommunityDAO();
		Comment comment = new Comment();
		Message msg = new Message();
		
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
	}
	}
	

}
