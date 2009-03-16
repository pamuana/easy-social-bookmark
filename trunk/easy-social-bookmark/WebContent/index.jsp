<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="br.bookmark.db.Database"%>
<%@page import="br.bookmark.db.DataBaseUtils"%>
<%@page import="br.bookmark.project.Init"%>
<%@page import="java.sql.Connection"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=UTF-8" http-equiv="content-type">
  <title>Login</title>
</head>
<body>
<%
    
    Init.init("jdbc:mysql://localhost/bookmarks?user=root&amp;password=");
    


%>
Login<br/>
<a href="register.jsp">sign up</a><br/>
<form action="viewbookmark.jsp" method="post" name="login">
Username:<br/>
  <input name="username">
  <br/>
  <br/>
Password:
  <br/>
  <input name="password" type="password">
  <br/>
  <br/>
  <a href="forgot.jsp">forgot password</a>
   <br/>
  <input name="send" value="Send" type="submit">
  <br/>
  <br/>
</form>
</body>
</html>
