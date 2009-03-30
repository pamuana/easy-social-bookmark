<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Forgot Password</title>
<link rel="stylesheet" href="../css/style.css" type="text/css" />
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

    <form action="Forgot" method="post">
        <div>Type your email address: </div>
        <br />
        <input type="text" name="email"></input>
        <br />
        <br />
        <input type="submit" value="Send" name="send"/>
        <input value="cancel" name="cancel" type="button" onclick="history.back()"/>
    </form>

    <p/>
 
 		 <br />
         <br />
         <br />
         <br />
         <br />
         <br />
         <br />
       	  <div id="sidebar"></div>
          <div class="clear"> </div>
          <div id="footer"></div>
        </div>
	</div>
</div>
</div>
</body>
</html>