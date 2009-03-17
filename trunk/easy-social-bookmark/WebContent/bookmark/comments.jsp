<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
		CommentMgr commentMgr = new CommentMgr(bookmarkInit.getCommentDAO());
		BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
		Bookmark bookmark = bookmarkMgr.findById(request.getParameter("bookmark")+"");
		UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
		User user = userMgr.findById(""+session.getAttribute("idUser"));
		
		if(request.getParameter("operation")!=null){
			Comment comment =new Comment();
			comment.setText(request.getParameter("text")+"");
			comment.setIdBookmark(Long.parseLong(bookmark.getId()+""));
			comment.setIdUser(Long.parseLong(user.getId()+""));
			commentMgr.save(comment);
			String src= "../community/viewcommunity.jsp?community="+request.getParameter("community");
			
%>
    <SCRIPT type="text/javascript">
        window.location.href = "<%= src%>";
    </SCRIPT>
<%
			
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
	<input type="hidden" name="bookmark" value="<%=bookmark.getId() %>"/>
	<input type="hidden" name="community" value="<%=request.getParameter("community")%>"/>
	<textarea cols="50" rows="10" name="text" ></textarea>
	<br/>
	<input name="send" value="send" type="submit"/>
	<input name="cancel" value="cancel" type="button"/>
</form>
</body>
</html>