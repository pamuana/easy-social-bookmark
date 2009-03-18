<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
	TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
	
	String idUser=session.getAttribute("idUser").toString();
	
	CommunityMgr communityMgr=new CommunityMgr(bookmarkInit.getCommunityDAO(),bookmarkInit.getUserDAO());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Bookmark List</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css">
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
	    
<%
		Collection<String> bookmarkIds=new ArrayList<String>();
		if (request.getParameter("idTag")!=null){
			bookmarkIds=tagMgr.findIdBookmarksByIdTag(request.getParameter("idTag"));
		}
		
		Collection<Bookmark> bookmarks = bookmarkMgr.findBookmarksByIdUser(idUser);
		for (Bookmark bm : bookmarks) {
			boolean view=true;
			if (request.getParameter("idTag")!=null){
				view=bookmarkIds.contains(""+bm.getId());
			}
			if ((bm.getIdCommunity()==0)&&(view)){
%>
		<div class="node">
		    <h2 class="nodeTitle"><%=bm.getName()%> - (<%=bookmarkMgr.findByUrl(bm.getUrl()).size()%>)</h2>
		    <div class="post">
		    <div class="taxonomy">
				Tag's:
<%
			Collection<Tag> tags=tagMgr.findTagsByIdBookmark(""+bm.getId());
		    for (Tag tag : tags) {
%>
		       		&nbsp;<%=tag.getName()%>, &nbsp;&nbsp;&nbsp;
<%
		    	}
%>
			</div> 
		    <div class="shared"></div>
		    <div class="url"><%=bm.getUrl()%></div>
		    <ul class="links inline">
		    	<li class="comment_add first"><a href="bookmarkForm.jsp?operation=share&idBookmark=<%=bm.getId()%>">share</a></li>
		    	<li class="comment_add first"><a href="bookmarkForm.jsp?idBookmark=<%=bm.getId()%>">edit</a></li>
		    	<li class="comment_add first"><a href="bookmarkAction.jsp?operation=delete&idBookmark=<%= bm.getId() %>">delete</a></li>
		    </ul>

			</div>
		</div>
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
				Collection<Community> communities=communityMgr.findCommunitiesByIdUser(idUser);
				for (Community community:communities){
					if (community.getIdParent()==0){
%>
						<li><a href="bookmarkCommunityList.jsp?idCommunity=<%=community.getId()%>"><%=community.getName()%></a>
						<ul>
<%
						Collection<Community> subcommunities=communityMgr.findSubCommunity(""+community.getId());
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
					Collection<Tag> userTags=tagMgr.findTagsByIdUser(idUser);
					for (Tag tag:userTags){
%>
						<a href="bookmarkList.jsp?idTag=<%=tag.getId()%>"><%=tag.getName()%></a>
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
