<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.bookmark.db.Database"%>
<%@page import="br.bookmark.db.DataBaseUtils"%>
<%@page import="br.bookmark.project.Init"%>
<%@page import="java.sql.Connection"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%="Texto experimentado" %>
<hr/>
<%
	
	Init.init("jdbc:mysql://localhost/bookmarks?user=root&amp;password=");
	

%>
</body>
</html>