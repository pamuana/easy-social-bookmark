package br.bookmark.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.bookmark.project.*;
import br.bookmark.db.UserDAO;
import br.bookmark.models.*;

/**
 * Servlet implementation class AutorEdit
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			register(request,response);
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
			register(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");
		String msg = "";

		UserDAO userDAO = bookmarkInit.getUserDAO();
		if (userDAO == null){
			response.sendRedirect("error.jsp");
		}
		
		System.out.println("-->"+request.getParameter("login"));
		System.out.println("-->/"+request.getParameter("name"));
		System.out.println("-->//"+request.getParameter("password"));
		
		if (request.getParameter("send")!=null){
			if (userDAO.findByLogin(request.getParameter("login"))!=null){
				msg = "<b>The user login exits plz change the field login for other</b><hr/>";
			}else if (request.getParameter("password").equals(request.getParameter("confirmpassword"))){
				User user=new User();
				user.setLogin(request.getParameter("login"));
				user.setName(request.getParameter("name"));
				user.setEmail(request.getParameter("email"));
				user.setPassword(request.getParameter("password"));
				userDAO.save(user);
				response.sendRedirect("login.jsp");
			}else{
				msg="<b>Error: The field password and Confirm Password need to be equals</b><hr/>";
			}
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
		requestDispatcher.forward(request, response);
	}

	

}

