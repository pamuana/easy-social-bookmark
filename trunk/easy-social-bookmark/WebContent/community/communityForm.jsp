<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*"%>

<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    CommunityMgr communityMgr = new CommunityMgr(bookmarkInit.getCommunityDAO(), bookmarkInit.getUserDAO());     
    
    String idCommunity="";
    String name="";
    String description="";
    if(request.getParameter("community")!=null){
        Community community = communityMgr.findById(""+request.getParameter("community"));
        idCommunity = community.getId()+"";
        name = community.getName();
        description = community.getDescription();
        
        
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Form Community</title>
</head>
 <body>
 <%
      if(request.getParameter("community")!=null){
 %>
<form action="communityAction.jsp" method="post" name="comments">
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
    <input name="send" value="send" type="submit"/>
    <input name="cancel" value="cancel" type="button"/>
</form>
<%
      }else if(request.getParameter("create")!=null){
%>
<form action="communityAction.jsp" method="post" name="comments">
    <input type="hidden" name="operation" value="createcommunity"/>
    Community name:
    <br/>
    <input type="text" name="name" ></input>
    <br/>
    Description:
    <br/>
    <textarea name="description" ></textarea>
    <br/>
    <input name="send" value="send" type="submit"/>
    <input name="cancel" value="cancel" type="button"/>
</form>
    
<%
      }else if(request.getParameter("addcommunity")!=null){
    	  
         
%>
<form action="communityAction.jsp" method="post" name="comments">
    <input type="hidden" name="operation" value="addcommunity"/>
    Select Communities:
    <br/>
 <%
 
		 Collection<Community> communities=communityMgr.findCommunities();
		 for (Community community : communities){
			 for(Community comm : communityMgr.findCommunitiesByIdUser(session.getAttribute("idUser").toString())){
				 if(comm.getId()!=community.getId())
					    out.println(community.getName()+"<input type=\"checkbox\" name=\"community"+community.getId()+"\" value=\""+community.getId()+"\" /><br/>");
			 }
		 }
 %>
    <input name="send" value="send" type="submit"/>
    <input name="cancel" value="cancel" type="button"/>
</form>
    
<%
          
      }
%>

</body>
</html>