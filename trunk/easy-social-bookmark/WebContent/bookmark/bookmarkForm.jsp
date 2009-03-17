<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    
	BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
    TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
    
    String name="";
    String url="";
    String description="";
	String tagsString="";
	String operation="new"; 
    if (request.getParameter("idBookmark")!=null){
    	Bookmark bookmark=bookmarkMgr.findById(request.getParameter("idBookmark"));
    	name=bookmark.getName();
    	url=bookmark.getUrl();
    	description=bookmark.getDescription();
    	Collection<Tag> tags = tagMgr.findTagsByIdBookmark(request.getParameter("idBookmark"));
    	for (Tag tag: tags){
    		tagsString=tagsString+tag.getName()+",";
    	}
    	if (tagsString.length()>0){
    		tagsString=tagsString.substring(0,tagsString.length()-1);
    	}
		operation="edit";
    }
    if (request.getParameter("operation")!=null&&request.getParameter("operation").equals("share")){
    	operation="share";
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Manage Bookmark</title>
</head>

<body>
<form name="editbookmark" action="bookmarkAction.jsp" method="post">
    <input type="hidden" name="operation" value="<%=operation%>"/>
    <% if (request.getParameter("idBookmark")!=null) {%>
    	<input type="hidden" name="idBookmark" value="<%=request.getParameter("idBookmark")%>"/>
    <%} %>
	Name:
	<br />
	<input name="name" value="<%=name%>" type="text" ></input>
	<br />
	<br />
	URL:
	<br />
	<input name="url" value="<%=url%>" type="text"  ></input>
	<br />
	<br />
	Description:
	<br />
	<input name="description" value="<%=description%>" type="text" ></input>
	<br />
	<br />
	Tags:
	<br />
	<textarea name="tags" cols="50" rows="10"><%=tagsString%></textarea>
	<br />
<%
	if (operation.equals("share")){
		CommunityMgr communityMgr=new CommunityMgr(bookmarkInit.getCommunityDAO(),bookmarkInit.getUserDAO());
		Collection<Community> communities=communityMgr.findCommunitiesByIdUser(session.getAttribute("idUser").toString());
		for (Community community : communities){
			out.println(community.getName()+"<input type=\"checkbox\" name=\"community"+community.getId()+"\" value=\""+community.getId()+"\" /><br/>");
		}
	}
%>
	<input name="send" value="send" type="submit" />
	<input name="cancel" type="button" value="Cancel"/>
</form>

</body></html>
