<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    CommunityMgr communityMgr = new CommunityMgr(bookmarkInit.getCommunityDAO(), bookmarkInit.getUserDAO()); 
    MessageMgr messageMgr= new MessageMgr(bookmarkInit.getMessageDAO());    
    String idUser=""+session.getAttribute("idUser");  
    String idCommunty=request.getParameter("idCommunity"); 
    Community community = communityMgr.findById(idCommunty);
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Community Messages</title>

  <style type="text/css">
.tree { overflow: auto; width: 15em; height: 25em; cursor: default; border: 1px solid gray; padding-left: .5em;}
.treeitem { margin-right: .6em;}
.collapsedgroup { display : none; }
.group { margin-left: 1.5em; }
.treeitemfocus { outline: 0px; background-color: #dddddd; color: black;}
  </style>
  <link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
<div style="text-align: left; width: 150px;">
<div class="menuheader">Main Menu</div>
<!-- begin left menu -->
    <div id="LeftMNav">
    <ul>
        <li><a title="view bookmark" href="../bookmark/bookmarkList.jsp">&raquo;&nbsp;View Bookmark</a></li>
        <li><a title="new bookmark" href="../bookmark/bookmarkForm.jsp">&raquo;&nbsp;New Bookmark</a></li>
        <li><a title="view interesting" href="#">&raquo;&nbsp;View Interesting</a></li>
        <li><a title="view statistic" href="#">&raquo;&nbsp;View Statistics</a></li>
    </ul>
    <hr/>
    <ul>
        <li><a title="list communities" href="communityList.jsp">&raquo;&nbsp;List Communities</a></li>
        <li><a title="new community" href="communityForm.jsp?create=create">&raquo;&nbsp;New Community</a></li>
        <li><a title="add community" href="communityForm.jsp?addcommunity=addcommunity">&raquo;&nbsp;Add Community</a></li>
    </ul>
    </div>
    <!-- end left menu -->

    <!-- begin right menu -->
    <div id="right-menu">
        <div id="tree">
          <a title="list communities" href="communityList.jsp">List Communities</a>
            <ul>
<%
        // TODO criar uma funç~ao recursiva para esta chamada
        Collection<Community> communities=communityMgr.findCommunitiesByIdUser(idUser);
        for (Community comm :communities){
            if (comm.getIdParent()==0){
%>
                <li><a href="#"><%=comm.getName()%></a></li>
                    <ul>
<%
                        Collection<Community> subcommunities=communityMgr.findSubCommunity(""+comm.getId());
                        for (Community subcommunity:subcommunities){
%>
                            <li><a href="#"><%=subcommunity.getName()%></a></li>
<%
                        }
%>
                    </ul>
<%          
            }
        }
%>
            </ul>
        </div>
    </div>

    </div>
    <!-- end right menu -->
<div id="center">
    <div class="namecommunity"><%= community.getName() %></div>
    <div class="communitydescription"><%= community.getDescription() %></div>
    <div class="newmessage">
        <a>Add Message</a>
    </div>
<%
     for(Message message : messageMgr.findMessagesByIdCommunity(idCommunty)){    
%>  
     <div class="message"><%= message.getText() %></div>
<%
     }
%>
</div>
</body>
</html>
