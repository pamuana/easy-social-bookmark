<%@page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.db.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="br.bookmark.project.Init"%>
<%@page import="java.util.*" %>
<%@taglib uri="br.bookmark.project" prefix="Widget" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
	TagDAO tagDAO = bookmarkInit.getTagDAO();
	
	String idUser=session.getAttribute("idUser").toString();
	String idCommunity = request.getParameter("idCommunity").toString();
	String idTag = request.getParameter("idTag");
	
	CommunityDAO communityDAO= bookmarkInit.getCommunityDAO();
	CommentDAO commentDAO= bookmarkInit.getCommentDAO();
	
	Community currentCommunity=communityDAO.findById(Long.parseLong(idCommunity));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Community Bookmark List</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
</head>
<body>
<div id="wrap">
	<div id="container">
	
		<div id="header">
			<div id="caption">
				<div id="title">
					<h1>Easy Bookmark Social</h1>
					<p>Community list of bookmarks </p>
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
	
    			<div id="community">
        			<h2>You are viewing shared bookmarks in <b><%=currentCommunity.getName() %></b></h2>
        			<div class="descriptioncommunity">
        				<%= currentCommunity.getDescription() %>
        			</div>
    			</div>
    			<p/>&nbsp;
    			<hr/>
    			<p/>&nbsp;
				<%
		 		if (idTag!=null&&!"".equals(idTag)){
				%>
				<Widget:BookmarkList idUser="<%=idUser%>" tagDAO="<%=tagDAO%>" bookmarkDAO="<%=bookmarkDAO%>" communityDAO="<%=communityDAO%>" commentDAO="<%=commentDAO%>" idCommunity="<%=idCommunity%>" idTag="<%=idTag%>" />
				<%
		 		}else{
				%>
				<Widget:BookmarkList idUser="<%=idUser%>" tagDAO="<%=tagDAO%>" bookmarkDAO="<%=bookmarkDAO%>" communityDAO="<%=communityDAO%>" commentDAO="<%=commentDAO%>" idCommunity="<%=idCommunity%>" />
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
						<Widget:MenuTag idUser="<%=idUser%>"tagDAO="<%=tagDAO%>" idCommunity="<%=idCommunity%>"/>
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
