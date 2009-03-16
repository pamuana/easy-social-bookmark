<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.db.Database"%>
<%@page import="br.bookmark.db.DataBaseUtils"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<% 
	Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
    if (userMgr == null){
    	response.sendRedirect("error.jsp");
    }
    
	//Le valores passados por p
	String login = request.getParameter("login");
	String password = request.getParameter("password");

	if ((login != null)&&(password!=null)) {
	
		// Verifica o login e senha
		User user = userMgr.validateUser(login, password);
		if (user == null) {
			response.sendRedirect("error.jsp");
		} else {
			// Login valido
			session.setAttribute("idUser", user.getId());
			response.sendRedirect("viewbookmark.jsp");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type" />
<title>Login</title>
</head>
<body>
Login
<br />
<a href="register.jsp">sign up</a>
<br />
<form action="login.jsp" method="post" name="formLogin">
Username:<br />
<input name="login" /> <br />
<br />
Password: <br />
<input name="password" type="password" /> <br />
<br />
<a href="forgot.jsp">forgot password</a> <br />
<input name="send" value="Send" type="submit" /> <br />
<br />
</form>
</body>
</html>