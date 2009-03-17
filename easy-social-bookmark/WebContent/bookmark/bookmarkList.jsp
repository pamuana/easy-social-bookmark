<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
	TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
	UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
	
	String idUser=session.getAttribute("idUser").toString();
	User user = userMgr.findById(idUser);
	
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

<!-- begin left menu -->
	<div id="LeftMNav">
	<ul>
  		<li><a title="new bookmark" href="bookmarkForm.jsp">&raquo;&nbsp;New Bookmark</a></li>
  		<li><a title="Forum Help/Guide" href="http://www.ex-designz.net/faqhelp.asp">&raquo;&nbsp;Forum Help</a></li>
  		<li><a title="Browse through our hundreds of free articles" href="http://www.ex-designz.net/article.asp">&raquo;&nbsp;Article Directory</a></li>
  		<li><a title="Free sing-along lyrics with tune" href="http://www.ex-designz.net/lyricskaraoke.asp">&raquo;&nbsp;Music</a></li>
  		<li><a title="Prank,Filipino,computer and cebuano jokes" href="http://www.ex-designz.net/extremejoke.asp">&raquo;&nbsp;Jokes</a></li>
  		<li><a title="Photos of sexy H-wood beauties,hunks and Pinay babes" href="http://www.ex-designz.net/gallery/default.asp">&raquo;&nbsp;Photo Gallery</a></li>
  		<li><a title="Download freeware,midi and web template" href="http://www.ex-designz.net/dlcentral.asp">&raquo;&nbsp;Download Central</a></li>
  		<li><a title="Link Directory" href="http://www.ex-designz.net/directory/default.asp">&raquo;&nbsp;Link Directory</a></li>
  		<li><a title="Recipe" href="http://www.ex-designz.net/recipemain.asp">&raquo;&nbsp;World Recipe</a></li>
		<li><a title="Find,make friends at Ex-designz dating service" href="http://www.ex-designz.net/dating.asp">&raquo;&nbsp;Online Dating</a></li>
		<li><a title="Horoscope-Astrology" href="http://www.ex-designz.net/horoscope.asp">&raquo;&nbsp;Horoscope</a></li>
		<li><a title="Headline News from around the world" href="http://www.ex-designz.net/topstorynews.asp">&raquo;&nbsp;Headline News</a></li>
	</ul>
	</div>
	<!-- end left menu -->

	<!-- begin right menu -->
	<div id="right-menu">
		<div id="tree">
			<ul>
<%
		// TODO criar uma funç~ao recursiva para esta chamada
		Collection<Community> communities=communityMgr.findCommunitiesByIdUser(idUser);
		for (Community community:communities){
			if (community.getIdParent()==0){
%>
				<li><a href="#"><%=community.getName()%></a></li>
					<ul>
<%
						Collection<Community> subcommunities=communityMgr.findSubCommunity(""+community.getId());
						for (Community subcommunity:subcommunities){
%>
							<li><a href="#"><%=subcommunity.getName()%></a></li>
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
			<li><a href="#"><%=tag.getName()%></a></li>
<%	
		}
%>
		</ul>
		</div>
	</div>

	</div>
	<!-- end right menu -->
	<div id="center">
	    <div class="bookmark">
<%
	
		for (Bookmark bm : bookmarkMgr.findBookmarksByIdUser(user.getId()+"")) {
			if (bm.getIdCommunity()==0){
%>
		    <div class="name"><%=bm.getName()%></div>
		    <div class="shared"><%= bookmarkMgr.findByUrl(bm.getUrl()).size() %></div>
		    <div class="url"><%=bm.getUrl()%></div>
		    <div class="commands"><a href="bookmarkForm.jsp?operation=share&idBookmark=<%=bm.getId()%>">share</a>,<a href="bookmarkForm.jsp?idBookmark=<%=bm.getId()%>">edit</a>,<a href="bookmarkAction.jsp?operation=delete&idBookmark=<%= bm.getId() %>">delete</a></div>
<%
		    for (Tag tag : tagMgr.findTagsByIdBookmark(bm.getId()+"")) {
%>
	        <div class="tags"><%=tag.getName()%></div>
<%
		    	}
			}
		}
%>
	    </div>
	</div>
</div>
</body>
</html>
