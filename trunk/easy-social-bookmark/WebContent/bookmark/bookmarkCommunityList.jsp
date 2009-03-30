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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Community Bookmark List</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css">
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
<%
    				Community currentCommunity=communityDAO.findById(Long.parseLong(idCommunity));
%>
        			<h2>Your are view shared bookmarks in <b><%=currentCommunity.getName() %></b></h2>
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
<%
					// TODO criar uma funç~ao recursiva para esta chamada
				Collection<Community> communities=communityDAO.findCommunitiesByIdUser(Long.parseLong(idUser));
				for (Community community:communities){
					if (community.getIdParent()==0){
%>
						<li><a href="bookmarkCommunityList.jsp?idCommunity=<%=community.getId()%>"><%=community.getName()%></a>
						<ul>
<%
						Collection<Community> subcommunities=communityDAO.findByIdParent(community.getId());
						for (Community subcommunity:subcommunities){
%>
							<li><a href="bookmarkCommunityList.jsp?idCommunity=<%=subcommunity.getId()%>"><%=subcommunity.getName()%></a></li>
<%
						}
%>
						</ul>
						</li>
<%			
					}
				}
%>
					</ul>
					</div>
        		</div>
        		<p/>&nbsp;
        		<hr/>
        		<p/>&nbsp;
        		<div id="block-tags" class="block">
        			<h2>List of Tags</h2>
        			<div class="content">        			
<%
					Collection<Tag> communityTags=tagDAO.findTagsByIdCommunity(Long.parseLong(idCommunity));
					for (Tag tag:communityTags){
%>
						<a href="bookmarkCommunityList.jsp?idCommunity=<%=idCommunity%>&idTag=<%=tag.getId()%>"><%=tag.getName()%></a>
<%	
					}
%>
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
