/* Edita autor */
package br.bookmark.servlet;

import java.io.IOException;
import java.util.Collection;


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
public class CommunityAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			communityAdd(request,response);
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
			communityAdd(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void communityAdd(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
		UserDAO userDAO = bookmarkInit.getUserDAO();
		User user = userDAO.findById(Long.parseLong(""+session.getAttribute("idUser")));
		CommunityDAO communityDAO = bookmarkInit.getCommunityDAO();		
		
		Collection<Community> communities=communityDAO.findAll();
        for (Community community : communities){
            if (request.getParameter("community"+community.getId())!=null){
                communityDAO.assignCommunity(community.getId(),user.getId());
            }
        }
		

		try { 
			String url ="communityList.jsp";	    
			response.sendRedirect(url); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
