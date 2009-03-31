<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.db.*"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");
    String idUser = session.getAttribute("idUser").toString();
    CommunityDAO communityDAO=bookmarkInit.getCommunityDAO();
    UserDAO userDAO = bookmarkInit.getUserDAO();
    String idCommunity= request.getParameter("idCommunity");
    Community communinty = communityDAO.findById(Long.parseLong(idCommunity));
    
    if ((communityDAO == null)||(idCommunity.equals(""))||(idCommunity==null)){
        response.sendRedirect("error.jsp");
    }
    
    Collection<String> idUsers = communityDAO.findIdUsersByIdCommunity(Long.parseLong(idCommunity));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Manage Members</title>
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
<h2 class="nodeTitle"> <%= communinty.getName() %></h2>

<%
	for (String id : idUsers){
		User user = userDAO.findById(Long.parseLong(id));
%>
	<div class="member">
    	 <div class="login">Login: <%=user.getLogin()%></div>
         <div class="name">Name: <%=user.getName()%></div>
         <div class="email">Email: <%=user.getEmail()%></div>
        <br/>
		<a href="RemoveMember?operation=removeMember&idCommunity=<%=idCommunity%>&idUser=<%=user.getId()%>">remove</a>
	</div>
         <br />
         <br />
         <br />
         <br />
         <br />
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
                </div>
            </div>
        <div class="clear"> </div>
        <div id="footer"></div>
      </div>  
</div>
</body>
</html>