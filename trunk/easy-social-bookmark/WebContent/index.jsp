<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="commons.db.Database"%>
<%@page import="commons.db.DataBaseUtils"%>
<%@page import="bookmark.br.Init"%>
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
	
	Init.init("jdbc:mysql://localhost/bookmark?user=root&amp;password=");
	
	//Connection dbCon=database.createConnection();
	
	//dbCon.close();
	//dbCon = database.createConnection();

	
	
	//aRs = dbCon.createStatement().executeQuery("SELECT COUNT(*) FROM GCFF_Component");
	//aRs.next();
	//assertEquals("1",aRs.getString(1));
	
	//BookmarkDAO bookmarkdao=new BookmarkDAO("tr",db);
	

%>
</body>
</html>