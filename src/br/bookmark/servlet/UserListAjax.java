package br.bookmark.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.bookmark.db.UserDAO;
import br.bookmark.models.User;
import br.bookmark.project.Init;

/**
 * Servlet implementation class AutorEdit
 */
public class UserListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");
		this.userDAO = bookmarkInit.getUserDAO();
		
				
		String operation="";
		String str="";
		if (request.getParameter("operation")!=null){
			operation=request.getParameter("operation").toString();
		}
		if (request.getParameter("str")!=null){
			str=request.getParameter("str").toString();
		}
		
		if ("all".equals(operation)){
			try {
				userSuggestionNameList(str, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("exist".equals(operation)){
			try {
				userExistNameList(str, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");
		this.userDAO = bookmarkInit.getUserDAO();
		
				
		String operation="";
		String str="";
		if (request.getParameter("operation")!=null){
			operation=request.getParameter("operation").toString();
		}
		if (request.getParameter("str")!=null){
			str=request.getParameter("str").toString();
		}
		
		if ("all".equals(operation)){
			try {
				userSuggestionNameList(str, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("exist".equals(operation)){
			try {
				userExistNameList(str, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	


	private void userExistNameList(String str, HttpServletResponse response) throws Exception{
		User user=this.userDAO.findByLogin(str);
		if (user!=null){
			response.getWriter().print("the user name exists or the name is invalid");
			
		}else{
			response.getWriter().print("good!!!");
		}
	}

	private void userSuggestionNameList(String str, HttpServletResponse response) throws Exception {
		Collection<User> userList = this.userDAO.findUsersByLikeLogin(str);

		JSONArray loginsJSON = new JSONArray();
		for (User user:userList){
			loginsJSON.add(user.getLogin());
		}
		response.getWriter().print(loginsJSON);
	}

	
}

