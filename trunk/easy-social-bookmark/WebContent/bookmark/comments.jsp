<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
		CommentMgr commentMgr = new CommentMgr(bookmarkInit.getCommentDAO());
		String comment = "";
		if(request.getParameter("comment")!=null){
			Comment com = commentMgr.findById(request.getParameter("comment")+"");
			comment = com.getText();
		}
		
		if(request.getParameter("operation")!=null){
			Comment comments =new Comment();
			comments.setText(request.getParameter("text")+"");
			commentMgr.save(comments);
		}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Comments</title>
</head>
<body>
<form action="comments.jsp" method="post" name="comments">
	<input type="hidden" name="operation" value="operation"/>
	<textarea cols="50" rows="10" name="text" value="<%= comment %>"></textarea>
	<br/>
	<input name="send" value="send" type="submit"/>
	<input name="cancel" value="cancel" type="button"/>
</form>
</body>
</html>