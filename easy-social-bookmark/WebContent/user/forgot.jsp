<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	String email = request.getParameter("email");
	String pssw="";
	String login="";
	if(request.getParameter("email")!=null){
	    UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
	    User user = userMgr.findByEmail(email);
        if(user!=null){
		    pssw= user.getPassword();
		    login = user.getLogin();
        }
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Forgot Password</title>
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
<%
	if(request.getParameter("email")==null){
%>
    <form action="forgot.jsp" method="post">
        <div>Type your email address: </div>
        <br />
        <input type="text" name="email"></input>
        <br />
        <br />
        <input type="submit" value="Send" name="send"/>
    </form>
<%
	}else{
		if(!login.equals("")){
%>
    <form action="login.jsp" method="post">
        <div>login: <%= login%></div>
        <div>password: <%= pssw %> </div>
        <br/>
        <br />
        <input type="submit" value="Back" name="back"/>
    </form>
<%
		}else{
%>
    <form action="forgot.jsp" method="post">
        <div>Email not found</div>
        <br />
        <div>Type your email address: </div>
        <br />
        <input type="text" name="email"></input>
        <br />
        <br />
        <input type="submit" value="Send" name="send"/>
        <input value="cancel" name="cancel" type="button" onclick="history.back()">
    </form>

    <p>
      <%

		}
		
	}
%>
 		 <br />
         <br />
         <br />
         <br />
         <br />
         <br />
         <br />
    
          </div>
       	  <div id="sidebar"></div>
          <div class="clear"> </div>
          <div id="footer"></div>
        </div>
	</div>
</div>
</body>
</html>