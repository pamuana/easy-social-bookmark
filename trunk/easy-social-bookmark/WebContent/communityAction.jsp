<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	CommunityMgr communityMgr=new CommunityMgr(bookmarkInit.getCommunityDAO(),bookmarkInit.getUserDAO());
	
	String idCommunity = request.getParameter("idCommunity");
	String operation = request.getParameter("operation");
	String msg = "";
	String href= "";
	
	if (operation.equals("removeMember")){
		communityMgr.deassignCommunity(idCommunity,request.getParameter("idUser"));
		msg = "membro removido da comunidade com exito";
		href = "managemembers.jsp?idCommunity="+idCommunity;
	}
%>
	<SCRIPT type="text/javascript">
	<% if(!msg.equals("")) out.print("alert('"+msg+"');"); %>
   		window.location.href = "<%=href%>";
	</SCRIPT>		  	
	
  </body>
</html>