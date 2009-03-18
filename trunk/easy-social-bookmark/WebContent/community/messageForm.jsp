<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>

<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    CommunityMgr communityMgr = new CommunityMgr(bookmarkInit.getCommunityDAO(), bookmarkInit.getUserDAO());  
    MessageMgr messageMgr = new MessageMgr(bookmarkInit.getMessageDAO());
    String idCommunty=request.getParameter("idCommunity"); 
    String operation =request.getParameter("operation"); 
    Community community = communityMgr.findById(idCommunty);
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Message Form</title>
</head>
<body>
<%
	if(operation.equals("new")){
%>
<form action="messageAction.jsp">
<input type="hidden" name="idCommunity" value="<%= idCommunty %>"/>
<input type="hidden" name="operation" value="<%= operation %>"/>
<div>Community <%= community.getName() %></div>
<div class="message">
Message:
<br/>
<textarea rows="10" cols="50" name="message" ></textarea>
<br/>
<input type="submit" value="send" name="send" />
<input type="button" value="Cancel" name="cancel"/>
</div>
</form>
<%
	}else if(operation.equals("edit")){
		String idMessage =request.getParameter("idMessage");
		Message message = messageMgr.findById(idMessage);
%>
<form action="messageAction.jsp">
<input type="hidden" name="idCommunity" value="<%= idCommunty %>"/>
<input type="hidden" name="operation" value="<%= operation %>"/>
<div>Community <%= community.getName() %></div>
<div class="message">
Message:
<br/>
<textarea rows="10" cols="50" name="message" ><%= message.getText() %></textarea>
<br/>
<input type="submit" value="send" name="send" />
<input type="button" value="Cancel" name="cancel"/>
</div>
</form>
<%
    }
%>
</body>
</html>