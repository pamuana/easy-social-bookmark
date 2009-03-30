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
import br.bookmark.db.MessageDAO;
import br.bookmark.db.UserDAO;
import br.bookmark.models.*;

/**
 * Servlet implementation class AutorEdit
 */
public class MessageEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			messageEdit(request,response);
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
			messageEdit(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void messageEdit(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
		String idUser = (String) session.getAttribute("idUser");		
		String idCommunity =request.getParameter("idCommunity");
		MessageDAO messageDAO = bookmarkInit.getMessageDAO();
		
		String message = request.getParameter("message");
        Message msg = messageDAO.findById(Long.parseLong(idUser));
        msg.setIdCommunity(Long.parseLong(idCommunity));
        msg.setIdUser(Long.parseLong(idUser));
        msg.setText(message);
        messageDAO.save(msg);


		try { 
			String url ="communityMessage.jsp?idCommunity="+idCommunity;   
			response.sendRedirect(url); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
