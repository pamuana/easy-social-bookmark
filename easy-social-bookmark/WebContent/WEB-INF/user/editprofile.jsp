<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<html xmlns="http://www.w3.org/1999/xhtml">
  <title>Edit Profile</title>
</head>
<body>
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
	Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");

	UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
	String idUser = session.getAttribute("idUser").toString();
	User user = userMgr.findById(idUser);
	boolean edit=false;
	
	
	if ((userMgr == null) || (user == null)){
		response.sendRedirect("error.jsp");
	}
	
	if ((request.getParameter("send")!=null)){
		String login=(user.getLogin()!=null?user.getLogin():"");
		String name=(user.getName()!=null?user.getName():"");
		String email=(user.getEmail()!=null?user.getEmail():"");
		String password=(user.getPassword()!=null?user.getPassword():"");
		
		if (!login.equals(request.getParameter("login"))){
			if (userMgr.findByLogin(request.getParameter("login"))==null){
				user.setLogin(request.getParameter("login"));
				edit=true;
			} else out.println("<b>The user login exits plz change the field login for other</b><hr/>"); 
		}
		if (!name.equals(request.getParameter("name"))){
			user.setName(request.getParameter("name"));
			edit=true;
		}
		if (!email.equals(request.getParameter("email"))){
			user.setEmail(request.getParameter("email"));
			edit=true;
		}
		if (request.getParameter("password").equals(request.getParameter("confirmpassword"))){
			if (!password.equals(request.getParameter("password"))){
				user.setPassword(request.getParameter("password"));
				edit=true;
			}
		}else out.println("<b>Error: The field password and Confirm Password need to be equals</b><hr/>");
		
		if (edit){
			userMgr.save(user);
		}
	}
%>
            <form action="editprofile.jsp" name="formRegister" method="post">
                Login:<br />
                <input name="login" value="<%=user.getLogin()%>" /><br />
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
        <div id="sidebar"></div>
        <div class="clear"> </div>
        <div id="footer"></div>
       </div>   
	</div>
</div>
</body>
</html>