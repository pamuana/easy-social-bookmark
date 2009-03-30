<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="br.bookmark.db.*"%>
<%@page import="java.util.*"%>

<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    CommunityDAO communityDAO = bookmarkInit.getCommunityDAO();     
    
    String idCommunity="";
    String name="";
    String description="";
    if(request.getParameter("community")!=null){
        Community community = communityDAO.findById(Long.parseLong(""+request.getParameter("community")));
        idCommunity = community.getId()+"";
        name = community.getName();
        description = community.getDescription();
        
        
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Form Community</title>
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
      if(request.getParameter("community")!=null){
 %>
<form action="/CommunityManage" method="post" name="comments">
    <input type="hidden" name="operation" value="managecommunity"/>
    <input type="hidden" name="idCommunity" value="<%= idCommunity %>"></input>
    Community:
    <br/>
    <input type="text" name="name" value="<%= name %>"></input>
    <br/>
    Description:
    <br/>
    <textarea name="description" ><%= description %></textarea>
    <br/>
    <a href="<%= "managemembers.jsp?idCommunity="+idCommunity%>">Manage Members</a>
    <br/>
    <br/>
    <input name="send" value="send" type="submit"/>
    <input name="cancel" value="cancel" type="button" onclick="history.back()"/>
</form>
	<br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
<%
      }else if(request.getParameter("create")!=null){
%>
<form action="/CommunityNew" method="post" name="comments">
    <input type="hidden" name="operation" value="createcommunity"/>
    Community name:
    <br/>
    <input type="text" name="name" ></input>
    <br/>
    Description:
    <br/>
    <textarea name="description" ></textarea>
    <br/>
    <br/>
    <input name="send" value="send" type="submit"/>
    <input name="cancel" value="cancel" type="button" onclick="history.back()"/>
</form>
	<br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    
<%
      }else if(request.getParameter("addcommunity")!=null){
    	  
         
%>
<form action="/CommunityAdd" method="post" name="comments">
    <input type="hidden" name="operation" value="addcommunity"/>
    Select Communities:
    <br/>
 <%
 
		 Collection<Community> communities=communityDAO.findDiffCommunitiesByIdUser(Long.parseLong(session.getAttribute("idUser").toString()));
     
		 for (Community community : communities){
			 
			  out.println(community.getName()+"<input type=\"checkbox\" name=\"community"+community.getId()+"\" value=\""+community.getId()+"\" /><br/>");			 
		 }
		 
 %>
 	<br/>
    <br/>
    <input name="send" value="send" type="submit"/>
    <input name="cancel" value="cancel" type="button" onclick="history.back()"/>
</form>
	<br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    
<%
          
      }
%>
		</div>
        <div id="sidebar"></div>
        <div class="clear"> </div>
        <div id="footer"></div>
       </div>   
</div>
</body>
</html>