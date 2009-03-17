<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.db.Database"%>
<%@page import="br.bookmark.db.DataBaseUtils"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Manage Members</title>
</head>
<body>
<%
	Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");
	CommunityMgr communityMgr=new CommunityMgr(bookmarkInit.getCommunityDAO(),bookmarkInit.getUserDAO());
	String idCommunity= request.getParameter("idCommunity");
	
	if ((communityMgr == null)||(idCommunity.equals(""))||(idCommunity==null)){
		response.sendRedirect("error.jsp");
	}
	
	Collection<User> users = communityMgr.findUsersByIdCommunity(idCommunity);
%>
<div class="community"> community</div>

<%
	for (User user:users){
%>
	<div class="member">
		<div class="login"><%=user.getLogin()%></div>
		<div class="name"><%=user.getName()%></div>
		<div class="email"><%=user.getEmail()%></div>
		<a href="communityAction.jsp?operation=removeMember&idCommunity=<%=idCommunity%>&idUser=<%=user.getId()%>">remove</a>
	</div>
<%
	}
%>
</body>
</html>