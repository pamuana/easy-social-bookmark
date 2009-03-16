<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
    TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
    UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
    User user = userMgr.findById(""+session.getAttribute("idUser"));
    Bookmark bookmark = bookmarkMgr.findById(""+session.getAttribute("idUser"));
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Edit bookmark</title>


</head>

<body>
<form name="editbookmark" action="editbookmark.jsp" id="edit" method="post">
	Name:
	<br />
	<input name="name" value="<%= bookmark.getName() %>" type="text" ></input>
	<br />
	<br />
	URL:
	<br />
	<input name="url" value="<%= bookmark.getUrl() %>" type="text"  ></input>
	<br />
	<br />
	Descption:
	<br />
	<input name="description" value="<%= bookmark.getDescription() %>" type="text" ></input>
	<br />
	<br />
	<select multiple="multiple" name="tags"><option value="tag1">tag1</option><option value="tag2">tag2</option><option value="tag3">tag3</option><option value="tag4">tag4</option></select>
	<br />
	<input name="send" value="send" type="submit" />
</form>

</body></html>