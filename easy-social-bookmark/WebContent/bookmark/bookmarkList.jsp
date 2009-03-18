<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
	TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
	
	String idUser=session.getAttribute("idUser").toString();
	
	CommunityMgr communityMgr=new CommunityMgr(bookmarkInit.getCommunityDAO(),bookmarkInit.getUserDAO());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html template="true">
<head>
  <meta content="text/html; charset=ISO-8859-1"
 http-equiv="content-type">
  <title>View Bookmarks</title>
  <style type="text/css">
.tree { overflow: auto; width: 15em; height: 25em; cursor: default; border: 1px solid gray; padding-left: .5em;}
.treeitem { margin-right: .6em;}
.collapsedgroup { display : none; }
.group { margin-left: 1.5em; }
.treeitemfocus { outline: 0px; background-color: #dddddd; color: black;}
  </style>
  <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div style="text-align: left; width: 100%;">
	<div class="menuheader">Main Menu</div>
	<a title="edit profile" href="../user/editprofile.jsp">Edit your profile</a>
<!-- begin left menu -->
	<div id="LeftMNav">
	<ul>
  		<li><a title="view bookmark" href="bookmarkList.jsp">&raquo;&nbsp;View Bookmark</a></li>
  		<li><a title="new bookmark" href="bookmarkForm.jsp">&raquo;&nbsp;New Bookmark</a></li>
  		<li><a title="view interesting" href="#">&raquo;&nbsp;View Interesting</a></li>
  		<li><a title="view statistic" href="#">&raquo;&nbsp;View Statistics</a></li>
	</ul>
	<hr/>
	<ul>
        <li><a title="list communities" href="../community/communityList.jsp">&raquo;&nbsp;List Communities</a></li>
        <li><a title="new community" href="../community/communityForm.jsp?create=create">&raquo;&nbsp;New Community</a></li>
        <li><a title="add community" href="../community/communityForm.jsp?addcommunity=addcommunity">&raquo;&nbsp;Add Community</a></li>
    </ul>
	</div>
	<!-- end left menu -->

	<!-- begin right menu -->
	<div id="right-menu">
		<div id="tree">
		  <a title="list communities" href="../community/communityList.jsp">List Communities</a>
			<ul>
<%
		// TODO criar uma funç~ao recursiva para esta chamada
		Collection<Community> communities=communityMgr.findCommunitiesByIdUser(idUser);
		for (Community community:communities){
			if (community.getIdParent()==0){
%>
				<li><a href="bookmarkCommunityList.jsp?idCommunity=<%=community.getId()%>"><%=community.getName()%></a></li>
					<ul>
<%
						Collection<Community> subcommunities=communityMgr.findSubCommunity(""+community.getId());
						for (Community subcommunity:subcommunities){
%>
							<li><a href="bookmarkCommunityList.jsp?idCommunity=<%=subcommunity.getId()%>"><%=subcommunity.getName()%></a></li>
<%
						}
%>
					</ul>
<%			
			}
		}
%>
			</ul>
		</div>
		<div id="tags">
		<ul>
<%
		Collection<Tag> userTags=tagMgr.findTagsByIdUser(idUser);
		for (Tag tag:userTags){
%>
			<li><a href="bookmarkList.jsp?idTag=<%=tag.getId()%>"><%=tag.getName()%></a></li>
<%	
		}
%>
		</ul>
		</div>
	</div>

	</div>
	<!-- end right menu -->
	<div id="center">
	    
<%
		Collection<String> bookmarkIds=new ArrayList<String>();
		if (request.getParameter("idTag")!=null){
			bookmarkIds=tagMgr.findIdBookmarksByIdTag(request.getParameter("idTag"));
		}
		
		Collection<Bookmark> bookmarks = bookmarkMgr.findBookmarksByIdUser(idUser);
		for (Bookmark bm : bookmarks) {
			boolean view=true;
			if (request.getParameter("idTag")!=null){
				view=bookmarkIds.contains(""+bm.getId());
			}
			if ((bm.getIdCommunity()==0)&&(view)){
%>
		<div class="bookmark">
		    <div class="name"><%=bm.getName()%></div>
		    <div class="shared"><%= bookmarkMgr.findByUrl(bm.getUrl()).size() %></div>
		    <div class="url"><%=bm.getUrl()%></div>
		    <div class="commands">
		    	<a href="bookmarkForm.jsp?operation=share&idBookmark=<%=bm.getId()%>">share</a>,
		    	<a href="bookmarkForm.jsp?idBookmark=<%=bm.getId()%>">edit</a>,
		    	<a href="bookmarkAction.jsp?operation=delete&idBookmark=<%= bm.getId() %>">delete</a>
		    </div>
<%
			Collection<Tag> tags=tagMgr.findTagsByIdBookmark(""+bm.getId());
		    for (Tag tag : tags) {
%>
	        <div class="tags"><%=tag.getName()%></div>
<%
		    	}
%>
		</div>
<%
			}
		}
%>
	    
	</div>
</div>
</body>
</html>
