<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.bookmark.models.User" %>
<%@page import="br.bookmark.project.Init" %>
<%@taglib uri="br.bookmark.project" prefix="Widget" %>
<%
	Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 

	String idUser = session.getAttribute("idUser").toString();
	
	User user = bookmarkInit.getUserDAO().findById(Long.parseLong(idUser));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Edit Profile</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css" />
  <script type="text/javascript" src="../js/include.js" />
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
		</div>
        <div id="content">
       	  <div id="main">
       	  <%
if (request.getAttribute("msg")!=null){
	out.print(request.getAttribute("msg"));
}
%>
            <form action="EditProfile" name="formProfile" method="post">
                Login:<br />
                <input name="login" value="<%=user.getLogin()%>" onchange="javascript:exist(this.value)" /><br />
                <p/>Suggestions: <span id="suggestions"></span><p/>
                Name:<br />
                <input name="name" value="<%=user.getName()%>" /><br />
                Email:<br />
                <input name="email" value="<%=user.getEmail()%>" /><br />
                Password:<br />
                <input name="password" type="password" value="<%=user.getPassword()%>" /><br />
                Confirm Password:<br />
                <input name="confirmpassword" type="password" value="<%=user.getPassword()%>" /><br />
                <br />
                <input name="send" value="send" type="submit" /> &nbsp; <input value="cancel" name="cancel" type="button" onclick="history.back()" />
                <p/>
                <a href="../bookmark/bookmarkList.jsp">List your Bookmarks</a>
            </form>
            
             <br />
             <br />
             <br />
             <br />
             <br />
             <br />
             <br />
             <br />
             <br />
             <br />
    
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
        <div class="clear"> </div>
        <div id="footer"></div>
       </div>   
	</div>
</div>
</body>
</html>