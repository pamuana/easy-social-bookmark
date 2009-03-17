<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
    CommunityMgr communityMgr = new CommunityMgr(bookmarkInit.getCommunityDAO(), bookmarkInit.getUserDAO()); 
    TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
    CommentMgr commentMgr = new CommentMgr(bookmarkInit.getCommentDAO());
    UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
    User user = userMgr.findById(""+session.getAttribute("idUser"));
    MessageMgr messageMgr= new MessageMgr(bookmarkInit.getMessageDAO());
    
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
<div id="LeftMNav">
<ul>
<li><a title="Ex-designz homepage" href="http://www.ex-designz.net">&raquo;&nbsp;Home</a></li>
<li><a title="Forum Help/Guide" href="http://www.ex-designz.net/faqhelp.asp">&raquo;&nbsp;Forum
Help</a></li>
<li><a title="Browse through our hundreds of free articles" href="http://www.ex-designz.net/article.asp">&raquo;&nbsp;Article
Directory</a></li>
<li><a title="Free sing-along lyrics with tune" href="http://www.ex-designz.net/lyricskaraoke.asp">&raquo;&nbsp;Music</a></li>
<li><a title="Prank,Filipino,computer and cebuano jokes" href="http://www.ex-designz.net/extremejoke.asp">&raquo;&nbsp;Jokes</a></li>
<li><a title="Photos of sexy H-wood beauties,hunks and Pinay babes" href="http://www.ex-designz.net/gallery/default.asp">&raquo;&nbsp;Photo
Gallery</a></li>
<li><a title="Download freeware,midi and web template" href="http://www.ex-designz.net/dlcentral.asp">&raquo;&nbsp;Download
Central</a></li>
<li><a title="Link Directory" href="http://www.ex-designz.net/directory/default.asp">&raquo;&nbsp;Link
Directory</a></li>
<li><a title="Recipe" href="http://www.ex-designz.net/recipemain.asp">&raquo;&nbsp;World
Recipe</a></li>
<li><a title="Find,make friends at Ex-designz dating service" href="http://www.ex-designz.net/dating.asp">&raquo;&nbsp;Online
Dating</a></li>
<li><a title="Horoscope-Astrology" href="http://www.ex-designz.net/horoscope.asp">&raquo;&nbsp;Horoscope</a></li>
<li><a title="Headline News from around the world" href="http://www.ex-designz.net/topstorynews.asp">&raquo;&nbsp;Headline
News</a></li>
</ul>
</div>
</div>
<div id="left">
    <div id="messages">
<%
    for(Message message : messageMgr.findMessages()){
%>
        <div class="message"><%= message.getText() %></div>
<%
    }
%>
    </div>
</div>
<div id="center">
<%
    if(!(communityMgr.findCommunitiesByIdUser(user.getId()+"").size()==0)){
    for(Community comm : communityMgr.findCommunitiesByIdUser(user.getId()+"")){
%>
    <div id="community">
        <a class="namecommunity"><%= comm.getName() %></a>
        <div class="descriptioncommunity"><%= comm.getDescription() %></div>
        <div class="commands">
        <a href="<%= "../community/viewcommunity.jsp?community="+comm.getId() %>">share</a>,
<%
         if(comm.getIdAdmin() == user.getId()){
%>
          <a href="<%= "communityForm.jsp?community="+comm.getId() %>">edit</a>,<a href="<%= "communityList.jsp?deleteComm="+comm.getId() %>">delete</a>
<%
         }
%>          
        <a href="communityForm.jsp?create=create">Cretate Community</a>
        <a href="communityForm.jsp?addcommunity=addcommunity">Add Community</a>
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
