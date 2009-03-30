/* Edita autor */
package br.bookmark.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.bookmark.project.*;
import br.bookmark.db.CommunityDAO;
import br.bookmark.db.UserDAO;
import br.bookmark.models.*;

/**
 * Servlet implementation class AutorEdit
 */
public class RemoveMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			removeMember(request,response);
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
			removeMember(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void removeMember(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
		String idUser = (String) session.getAttribute("idUser");
		CommunityDAO communityDAO = bookmarkInit.getCommunityDAO();		
		String idCommunity =request.getParameter("idCommunity");
		
		communityDAO.deassignCommunity(Long.parseLong(idCommunity),Long.parseLong(idUser));
    	
    	String msg = "member removed successfull";
		

		try { 
			String url ="managemembers.jsp?idCommunity="+idCommunity+"msg="+msg;	    
			response.sendRedirect(url); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
