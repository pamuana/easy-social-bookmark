/* Edita autor */
package br.bookmark.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.bookmark.db.MessageDAO;
import br.bookmark.project.*;


/**
 * Servlet implementation class AutorEdit
 */
public class MessageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			messageDelete(request,response);
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
			messageDelete(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void messageDelete(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 		
		String idCommunity =request.getParameter("idCommunity");
		MessageDAO messageDAO = bookmarkInit.getMessageDAO();
		
		String idMessage = request.getParameter("idMessage");
		messageDAO.delete(Long.parseLong(idMessage));


		try { 
			String url ="communityMessage.jsp?idCommunity="+idCommunity;   
			response.sendRedirect(url); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
