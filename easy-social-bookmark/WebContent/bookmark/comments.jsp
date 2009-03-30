<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.bookmark.project.Init"%>
<%@taglib uri="br.bookmark.project" prefix="Widget" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");
	String idUser=session.getAttribute("idUser").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Comment Form</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
</head>
<body>
<div id="wrap">
	<div id="container">
	
		<div id="header">
			<div id="caption">
				<div id="title">
					<h1>Easy Bookmark Social</h1>
					<p>Web System Develpoment </p>
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
			<form action="CommentAdd" method="post" >
				<input type="hidden" name="operation" value="new"/>
				<input type="hidden" name="idBookmark" value="<%= request.getParameter("idBookmark")%>"/>
				Comments:
				<br/>
				<textarea cols="50" rows="10" name="text" ></textarea>
				<br/>
				<input name="send" value="send" type="submit"/>
				<input name="cancel" value="cancel" type="button"/>
			</form>

		</div>
		<!-- End Main -->
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
				<Widget:MenuCommunity idUser="<%=idUser%>" communityDAO="<%=bookmarkInit.getCommunityDAO()%>"/>
				</ul>
				</div>
        	</div>
        	<p/>&nbsp;
        	<hr/>
        	<p/>&nbsp;
        	<div id="block-tags" class="block">
        		<div class="content">
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

