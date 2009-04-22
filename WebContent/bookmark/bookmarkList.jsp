<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.Init"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="br.bookmark.db.BookmarkDAO"%>
<%@page import="br.bookmark.db.TagDAO"%>
<%@page import="br.bookmark.db.CommunityDAO"%>
<%@page import="java.util.*" %>
<%@taglib uri="br.bookmark.project" prefix="Widget" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
	TagDAO tagDAO = bookmarkInit.getTagDAO();
	
	String idUser=session.getAttribute("idUser").toString();
	String idTag=request.getParameter("idTag");
	
	CommunityDAO communityDAO= bookmarkInit.getCommunityDAO();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Bookmark List</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
</head>
<body>
<div id="wrap">
	<div id="container">
	
		<div id="header">
			<div id="caption">
				<div id="title">
					<h1>Easy Bookmark Social</h1>
					<p>Web System Development </p>
					<br />
			  	</div>
			</div>
			<div id="navigation">
				<div id="menus">
					<ul class="links">
					</ul>
				</div>
				<div id="searchbox"><a title="edit profile" href="../user/editprofile.jsp">Edit your profile</a></div>
			</div>
		</div>
		<div id="content">
			<div id="main">
		<%
		 if (idTag!=null&&!"".equals(idTag)){
		%>
			<Widget:BookmarkList idUser="<%=idUser%>" tagDAO="<%=tagDAO%>" bookmarkDAO="<%=bookmarkDAO%>" idTag="<%=idTag%>" />
		<%
		 }else{
		%>
			<Widget:BookmarkList idUser="<%=idUser%>" tagDAO="<%=tagDAO%>" bookmarkDAO="<%=bookmarkDAO%>" />
		<% 
		 }
		%>
			
			</div>
        	<div id="sidebar">
        		<div id="block-menu-principal" class="block">
        			<Widget:MenuPrincipal/>
        		</div>
        		<p/>&nbsp;
        		<hr/>
        		<p/>&nbsp;
        		<div id="block-menu-community" class="block">
        			<h2><a title="list communities" href="../community/communityList.jsp">List Communities</a></h2>
        			<div class="content">
					<ul class="menu">
					<Widget:MenuCommunity idUser="<%=idUser%>" communityDAO="<%=communityDAO%>"/>
					</ul>
					</div>
        		</div>
        		<p/>&nbsp;
        		<hr/>
        		<p/>&nbsp;
        		<div id="block-tags" class="block">
        			<h2>List of Tags</h2>
        			<div class="content">
						<Widget:MenuTag idUser="<%=idUser%>"tagDAO="<%=tagDAO%>"/>
					</div>
        		</div>
        	</div>
        	<div class="clear"></div>
        	<div id="footer"></div>
		</div>
	</div>
</div>
</body>
</html>
