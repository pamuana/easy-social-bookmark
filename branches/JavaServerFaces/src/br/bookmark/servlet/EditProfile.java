package br.bookmark.servlet;

import java.io.IOException;
import java.util.Collection;


import javax.servlet.RequestDispatcher;
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
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			editProfile(request,response);
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
			editProfile(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void editProfile(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");

		UserDAO userDAO = bookmarkInit.getUserDAO();
		String idUser = session.getAttribute("idUser").toString();
		User user = userDAO.findById(Long.parseLong(idUser));
		boolean edit=false;
		String msg = "";
		
		
		if ((userDAO == null) || (user == null)){
			response.sendRedirect("error.jsp");
		}
		
		if ((request.getParameter("send")!=null)){
			String login=(user.getLogin()!=null?user.getLogin():"");
			String name=(user.getName()!=null?user.getName():"");
			String email=(user.getEmail()!=null?user.getEmail():"");
			String password=(user.getPassword()!=null?user.getPassword():"");
			
			if (!login.equals(request.getParameter("login"))){
				if (userDAO.findByLogin(request.getParameter("login"))==null){
					user.setLogin(request.getParameter("login"));
					edit=true;
				} else msg="<b>The user login exits plz try to change the field login</b><hr/>"; 
			}
			if (!name.equals(request.getParameter("name"))){
				user.setName(request.getParameter("name"));
				edit=true;
			}
			if (!email.equals(request.getParameter("email"))){
				user.setEmail(request.getParameter("email"));
				edit=true;
			}
			if (request.getParameter("password").equals(request.getParameter("confirmpassword"))){
				if (!password.equals(request.getParameter("password"))){
					user.setPassword(request.getParameter("password"));
					edit=true;
				}
			}else msg = "<b>Error: The field password and Confirm Password need to be equals</b><hr/>";
			
			if (edit){
				userDAO.save(user);
			}
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("../user/editprofile.jsp");
		requestDispatcher.forward(request, response);
	}

	

}
