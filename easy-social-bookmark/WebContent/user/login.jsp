<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta content="text/html; charset=UTF-8" http-equiv="content-type" />
	<title>Login</title>
	<link rel="stylesheet" href="../css/style.css" type="text/css" />	
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
		</div>
		<div id="content">
          <div id="main">
          	<a href="register.jsp">Sign in</a> <br />
            <form action="Login" method="post" name="formLogin">
                    Username:<br />
                    <input name="login" /> <br />
                    <br />
                    Password: <br />
                    <input name="password" type="password" /> <br />
                    <br />
                    <a href="forgot.jsp">forgot password</a> <br />
                    <input name="send" value="Send" type="submit" /> <br />
                    <br />
                    
            </form>
            <p><br />
              <br />
            </p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p><br />
              <br />
            </p>
</div>
        	<div id="sidebar"></div>
        	<div class="clear"> </div>
        	<div id="footer"></div>
		</div>
	</div>
</div>
</body>
</html>