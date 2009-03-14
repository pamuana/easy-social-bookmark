<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="commons.db.Database"%>
<%@page import="commons.db.DataBaseUtils"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%="Texto experimentado" %>
<hr/>
<%
	Database db=new Database("MySQL","com.mysql.jdbc.Driver", "jdbc:mysql://localhost/DBbookmark?user=root&amp;password=");
	BookmarkDAO bookmarkco=new BookmarkDAO("",db);

%>
</body>
</html>