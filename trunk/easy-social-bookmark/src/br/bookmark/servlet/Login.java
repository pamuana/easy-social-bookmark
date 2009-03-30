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
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			login(request,response);
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
			login(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void login(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");

		UserDAO userDAO = bookmarkInit.getUserDAO();
		String idUser = session.getAttribute("idUser").toString();
		boolean edit=false;
		String msg = "";
		
		    if (userDAO == null){
		    	response.sendRedirect("error.jsp");
		    }
		    
			//Le valores passados por p
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			if ((login != null)&&(password!=null)) {
			
				// Verifica o login e senha
				User user = userDAO.validateUser(login, password);
				if (user == null) {
					response.sendRedirect("error.jsp");
				} else {
					// Login valido
					session.setAttribute("idUser", user.getId());
					response.sendRedirect("../bookmark/bookmarkList.jsp");
				}
			}
	}

	

}

