<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="br.bookmark.db.*"%>
<%@page import="java.util.*" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
	TagDAO tagDAO = bookmarkInit.getTagDAO();
	
	String idUser=session.getAttribute("idUser").toString();
	
	CommunityDAO communityDAO=bookmarkInit.getCommunityDAO();
	CommentDAO commentDAO= bookmarkInit.getCommentDAO();
	String idCommunity = request.getParameter("idCommunity").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Community Bookmark List</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>
<div id="wrap">
	<div id="container">
	
		<div id="header">
			<div id="caption">
				<div id="title">
					<h1>Easy Bookmark Social</h1>
					<p>Community list of bookmarks </p>
					<br />
			  	</div>
			</div>
			<div id="navigation">
				<div id="menus">
					<ul class="links">
					</ul>
				</div>
				<div id="searchbox"><a title="edit profile" href="../user/editprofile.jsp">Edit your profile</a></div>
			</div>
		</div>
		<div id="content">
		<div id="main">

    <div id="community">
<%
    Community currentCommunity=communityDAO.findById(Long.parseLong(idCommunity));
%>
        <h2>Your are view shared bookmarks in <b><%=currentCommunity.getName() %></b></h2>
        <div class="descriptioncommunity">
        	<%= currentCommunity.getDescription() %>
        </div>
    </div>
    <p/>&nbsp;
    <hr/>
    <p/>&nbsp;
<%
	
	Collection<String> bookmarkIds=new ArrayList<String>();
	if (request.getParameter("idTag")!=null){
		bookmarkIds=tagDAO.findIdBookmarksByIdTag(Long.parseLong(request.getParameter("idTag")));
	}

	Collection<Bookmark> bookmarks = bookmarkDAO.findBookmarksByIdCommunity(Long.parseLong(idCommunity));
	for(Bookmark bk : bookmarks){
		boolean view=true;
		if (request.getParameter("idTag")!=null){
			view=bookmarkIds.contains(""+bk.getId());
		}
		if (view){
%>
    <div class="node">
        <h2 class="nodeTitle"><a href="<%=bk.getUrl()%>" target="_blank"><%= bk.getName() %>&nbsp;&nbsp; (<%=bookmarkDAO.findByUrl(bk.getUrl()).size()%>)</a></h2>
        <div class="post">
		    <div class="taxonomy">
				Tag's:
<%
			for (Tag tag : tagDAO.findTagsByIdBookmark(bk.getId())) {
%>
        		&nbsp;<%=tag.getName()%>, &nbsp;&nbsp;&nbsp;
<%
			}
%>
			</div>
			<div class="url"><a href="<%=bk.getUrl()%>" target="_blank"><%=bk.getUrl()%></div>				
        	<div class="content"><%= bk.getDescription() %></div>
        	<div>
        		<a class="addcomment" href="comments.jsp?idBookmark=<%=bk.getId()%>">Add comment</a>
				<a class="editlinks" href="bookmarkForm.jsp?idBookmark=<%=bk.getId()%>">edit</a>,
<%
				if(currentCommunity.getIdAdmin() == Long.parseLong(idUser)){
%>
				;&nbsp;&nbsp;<a href="bookmarkAction.jsp?operation=delete&idBookmark=<%=bk.getId()%>">delete</a>
<%
        	}
%>
			</div>

			<div class="comments">
<%
				Collection<Comment> comments=commentDAO.findCommentsByIdBookmark(bk.getId());
				for (Comment comment : comments) {
%>
					<div class="comment"><%= comment.getText() %></div>
<%
				}
%>
        	</div>
		</div>
	</div>
	<p/>&nbsp;
    <hr/>
    <p/>&nbsp;
	
<%
		}
	}
%>
			</div>
        	<div id="sidebar">
        		<div id="block-menu-principal" class="block">
        			<h2>Main Menu</h2>
        			<div class="content">
						<ul>
  							<li><a title="view bookmark" href="bookmarkList.jsp">&raquo;&nbsp;View Bookmark</a></li>
  							<li><a title="new bookmark" href="bookmarkForm.jsp">&raquo;&nbsp;New Bookmark</a></li>
  							<li><a title="view interesting" href="#">&raquo;&nbsp;View Interesting</a></li>
  							<li><a title="view statistic" href="#">&raquo;&nbsp;View Statistics</a></li>
						</ul>
						<br/><p/>&nbsp;
						<ul>
        					<li><a title="list communities" href="../community/communityList.jsp">&raquo;&nbsp;List Communities</a></li>
        					<li><a title="new community" href="../community/communityForm.jsp?create=create">&raquo;&nbsp;New Community</a></li>
        					<li><a title="add community" href="../community/communityForm.jsp?addcommunity=addcommunity">&raquo;&nbsp;Add Community</a></li>
    					</ul>    			
        			</div>
        		</div>
        		<p/>&nbsp;
        		<hr/>
        		<p/>&nbsp;
        		<div id="block-menu-community" class="block">
        			<h2><a title="list communities" href="../community/communityList.jsp">List Communities</a></h2>
        			<div class="content">
					<ul class="menu">
<%
					// TODO criar uma funç~ao recursiva para esta chamada
				Collection<Community> communities=communityDAO.findCommunitiesByIdUser(Long.parseLong(idUser));
				for (Community community:communities){
					if (community.getIdParent()==0){
%>
						<li><a href="bookmarkCommunityList.jsp?idCommunity=<%=community.getId()%>"><%=community.getName()%></a>
						<ul>
<%
						Collection<Community> subcommunities=communityDAO.findByIdParent(community.getId());
						for (Community subcommunity:subcommunities){
%>
							<li><a href="bookmarkCommunityList.jsp?idCommunity=<%=subcommunity.getId()%>"><%=subcommunity.getName()%></a></li>
<%
						}
%>
						</ul>
						</li>
<%			
					}
				}
%>
					</ul>
					</div>
        		</div>
        		<p/>&nbsp;
        		<hr/>
        		<p/>&nbsp;
        		<div id="block-tags" class="block">
        			<h2>List of Tags</h2>
        			<div class="content">        			
<%
					Collection<Tag> communityTags=tagDAO.findTagsByIdCommunity(Long.parseLong(idCommunity));
					for (Tag tag:communityTags){
%>
						<a href="bookmarkCommunityList.jsp?idCommunity=<%=idCommunity%>&idTag=<%=tag.getId()%>"><%=tag.getName()%></a>
<%	
					}
%>
					</div>
        		</div>
        	</div>
        	<div class="clear"></div>
        	<div id="footer"></div>
		</div>
	</div>
</div>
</body>
</html>
