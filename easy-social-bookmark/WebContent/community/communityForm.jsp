<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    CommunityMgr communityMgr = new CommunityMgr(bookmarkInit.getCommunityDAO(), bookmarkInit.getUserDAO());     
    Community community = communityMgr.findById(""+request.getParameter("community"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Form Community</title>
</head>
 <body>
<form action="communityAction.jsp" method="get" name="comments">
    <input type="hidden" name="operation" value="managecommunity"/>
    <input type="hidden" name="idCommunity" value="<%= community.getId() %>"></input>
    <input type="text" name="name" value="<%= community.getName() %>"></input>
    <br/>
    <input name="description" value="<%= community.getDescription() %>" ></input>
    <br/>
    <a href="<%= "managemembers.jsp?idCommunity="+community.getId() %>">Manage Members</a>
    <br/>
    <input name="send" value="send" type="submit"/>
    <input name="cancel" value="cancel" type="button"/>
</form>
</body>
</html>