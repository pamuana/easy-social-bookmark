<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@page import="br.bookmark.project.Init" %>
<%
	Init.init("jdbc:mysql://localhost/bookmarks?user=root&amp;password=","");
    Init bookmarkInit=new Init();
    
	String errorCode = request.getParameter("errorCode");
	 
	if (request.getParameter("logout") == null) {
		// New Session A
		session.setAttribute("bookmarkInit", bookmarkInit);
	    response.sendRedirect("user/login.jsp?errorCode="+errorCode);
	} else {
		// Logout A
		session.invalidate();
	    response.sendRedirect("index.jsp?errorCode=3");
	}
%>