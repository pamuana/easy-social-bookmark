<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Forgot Password</title>
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
					<p>Web System Development</p>
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
            Register<br/>
            <form action="Register" name="registerForm" method="post">
                Login:<br/>
                <input name="login" value="<%=(request.getParameter("login")!=null?request.getParameter("login"):"")%>" onchange="javascript:exist(this.value)" /><br/>
                <p/>Suggestions: <span id="suggestions"></span><p/>
                Name:<br/>
                <input name="name" value="<%=(request.getParameter("name")!=null?request.getParameter("name"):"")%>"/><br/>
                Email:<br/>
                <input name="email" value="<%=(request.getParameter("email")!=null?request.getParameter("email"):"")%>"/><br/>
                Password:<br/>
                <input name="password" type="password"/><br/>
                Confirm Password:<br/>
                <input name="confirmpassword" type="password"/><br/>
                <br/>
                <input name="send" value="send" type="submit"/> &nbsp;
                <input value="cancel" name="cancel" type="button" onclick="history.back()"/>
            </form>
            <br/>
            <br/>
            <br/>
			<br/>
            <br/>
            <br/>
			<br/>
        </div>
        <div id="sidebar"></div>
        	<div class="clear"> </div>
        	<div id="footer"></div>
        </div>
       </div>
   </div>
</body>
</html>
