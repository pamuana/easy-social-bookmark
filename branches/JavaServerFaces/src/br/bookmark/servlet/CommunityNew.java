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
public class CommunityNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			communityNew(request,response);
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
			communityNew(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void communityNew(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
		UserDAO userDAO = bookmarkInit.getUserDAO();
		User user = userDAO.findById(Long.parseLong(""+session.getAttribute("idUser")));
		CommunityDAO communityDAO = bookmarkInit.getCommunityDAO();		
		

		Community comm = new Community();
    	comm.setName(request.getParameter("name"));
    	comm.setDescription(request.getParameter("description"));
    	comm.setIdAdmin(user.getId());
    	communityDAO.save(comm);
    	
    	String msg = "community created with success";
		

		try { 
			String url ="communityList.jsp?msg="+msg;	    
			response.sendRedirect(url); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
