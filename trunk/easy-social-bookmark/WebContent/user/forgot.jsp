<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	String email = request.getParameter("email");
	String pssw="";
	String login="";
	if(request.getParameter("email")!=null){
	    UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
	    User user = userMgr.findByEmail(email);
	    pssw= user.getPassword();
	    login = user.getLogin();
    
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Forgot Password</title>
</head>
<body>
<%
	if(request.getParameter("email")==null){
%>
<form action="forgot.jsp">
	<div>Type your email address: </div>
	<br />
	<input type="text" name="email"></input>
	<br />
	<input type="submit" value="Send" name="send"/>
</form>
<%
	}else{
		if(!login.equals("")){
%>
<div>login: <%= login%></div>
<div>password: <%= pssw %> </div>
<%
		}else{
%>
<form action="forgot.jsp">
    <div>Email not found</div>
    <br />
    <div>Type your email address: </div>
    <br />
    <input type="text" name="email"></input>
    <br />
    <input type="submit" value="Send" name="send"/>
</form>

<%

		}
		
	}
%>
</body>
</html>