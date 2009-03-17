<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.db.Database"%>
<%@page import="br.bookmark.db.DataBaseUtils"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta content="text/html; charset=UTF-8" http-equiv="content-type">
  <title>register</title>
</head>
<body>
<%
	
	Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");
	UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
	if (userMgr == null){
		response.sendRedirect("error.jsp");
	}
	
	if (request.getParameter("send")!=null){
		if (userMgr.findByLogin(request.getParameter("login"))!=null){
			out.println("<b>The user login exits plz change the field login for other</b><hr/>");
		}else if (request.getParameter("password").equals(request.getParameter("confirmpassword"))){
			User user=new User();
			user.setLogin(request.getParameter("login"));
			user.setName(request.getParameter("name"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			userMgr.save(user);
			response.sendRedirect("login.jsp");
		}else{
			out.println("<b>Error: The field password and Confirm Password need to be equals</b><hr/>");
		}
	}
%>
Register<br>
<form action="register.jsp" name="registerForm" method="post">
	Login:<br>
	<input name="login" value="<%=(request.getParameter("login")!=null?request.getParameter("login"):"")%>"><br>
	Name:<br>
	<input name="name" value="<%=(request.getParameter("name")!=null?request.getParameter("name"):"")%>"><br>
	Email:<br>
	<input name="email" value="<%=(request.getParameter("email")!=null?request.getParameter("email"):"")%>"><br>
	Password:<br>
	<input name="password" type="password"><br>
	Confirm Password:<br>
	<input name="confirmpassword" type="password"><br>
	<br>
	<input name="send" value="send" type="submit"> &nbsp; <input value="cancel" name="cancel" type="button">
</form>

</body>
</html>
