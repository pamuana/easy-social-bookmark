<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="br.bookmark.db.*"%>
<%@page import="java.util.*" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    CommunityDAO communityDAO =bookmarkInit.getCommunityDAO(); 
    MessageDAO messageDAO= bookmarkInit.getMessageDAO();    
    String idUser=""+session.getAttribute("idUser");  
    String idCommunty=request.getParameter("idCommunity"); 
    Community community = communityDAO.findById(Long.parseLong(idCommunty));
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Community Messages</title>
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
                        
                <h2 class="nodeTitle">Community <%= community.getName() %></h2>
                <div class="communitydescription">Description <%= community.getDescription() %></div>
            
                <div><a href="<%= "messageForm.jsp?idCommunity="+idCommunty+"&operation=new" %>">Add Message</a></div>
				
<%
     for(Message message : messageDAO.findMessagesByIdCommunity(Long.parseLong(idCommunty))){    
%>  
                <div class="node">
                    <div class="post">
					     <div class="message">
					     <%= message.getText() %>
					     <br />
	<%
    if(message.getIdUser()==(Long.parseLong(idUser))){
%>
						     <div class="commands">
						        <a class="editlinks" href="<%= "messageForm.jsp?idCommunity="+idCommunty+"&idMessage="+message.getId()+"&operation=edit" %>">edit</a>
						        <a href="<%= "MessageDelete?idCommunity="+idCommunty+"&idMessage="+message.getId()+"&operation=delete"%>">delete</a>
						     </div>
<%
    }
%>
                        </div>
                     </div>
                    <p/>&nbsp;
	                <hr/>
	                <p/>&nbsp;
                </div>
<%
     }
%>
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
</div>
</body>
</html>
