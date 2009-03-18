<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
    CommunityMgr communityMgr = new CommunityMgr(bookmarkInit.getCommunityDAO(), bookmarkInit.getUserDAO()); 
    TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
    CommentMgr commentMgr = new CommentMgr(bookmarkInit.getCommentDAO());
    MessageMgr messageMgr= new MessageMgr(bookmarkInit.getMessageDAO());
    
    String idUser=""+session.getAttribute("idUser");
    
    if(request.getParameter("deleteComm")!=null){
    	communityMgr.deleteCommunity(request.getParameter("deleteComm")+"");
    }
    
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>List Community</title>

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
        for (Community community:communities){
            if (community.getIdParent()==0){
%>
                <li><a href="#"><%=community.getName()%></a></li>
                    <ul>
<%
                        Collection<Community> subcommunities=communityMgr.findSubCommunity(""+community.getId());
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
<%
    if(!(communityMgr.findCommunitiesByIdUser(idUser).size()==0)){
    for(Community comm : communityMgr.findCommunitiesByIdUser(idUser)){
%>
    <div id="community">
        <a class="namecommunity" href="<%= "communityMessage.jsp?idCommunity="+comm.getId() %>"><%= comm.getName() %></a>
        <div class="descriptioncommunity"><%= comm.getDescription() %></div>
        <div class="commands">
<%
         if(comm.getIdAdmin()==Long.parseLong(idUser)){
%>
          <a href="<%= "communityForm.jsp?community="+comm.getId() %>">edit</a>,
          <a href="<%= "communityList.jsp?deleteComm="+comm.getId() %>">delete</a>,
          <a href="<%= "managemembers.jsp?idCommunity="+comm.getId()%>">Manage Members</a>
<%
         }
%>         
        </div> 
    </div>
<%
    }
    }else{
%>
    <div id="community">
        There are no community to this user.
        <br />
        <div class="commands">
            <a href="communityForm.jsp?create=create">Cretate Community</a>
            <a href="communityForm.jsp?addcommunity=addcommunity">Add Community</a>
        </div>
    </div>
<%
    }
%>
</div>
</body>
</html>