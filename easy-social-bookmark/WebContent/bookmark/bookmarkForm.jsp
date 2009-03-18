<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    
	BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
    TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
    CommunityMgr communityMgr = new CommunityMgr(bookmarkInit.getCommunityDAO(),bookmarkInit.getUserDAO());
    
    String idUser = session.getAttribute("idUser").toString();
    
    String name="";
    String url="";
    String description="";
	String tagsString="";
	String operation="new"; 
    if (request.getParameter("idBookmark")!=null){
    	Bookmark bookmark=bookmarkMgr.findById(request.getParameter("idBookmark"));
    	name=bookmark.getName();
    	url=bookmark.getUrl();
    	description=bookmark.getDescription();
    	Collection<Tag> tags = tagMgr.findTagsByIdBookmark(request.getParameter("idBookmark"));
    	for (Tag tag: tags){
    		tagsString=tagsString+tag.getName()+",";
    	}
    	if (tagsString.length()>0){
    		tagsString=tagsString.substring(0,tagsString.length()-1);
    	}
		operation="edit";
    }
    if (request.getParameter("operation")!=null&&request.getParameter("operation").equals("share")){
    	operation="share";
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Bookmark Form</title>
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
			<form name="editbookmark" action="bookmarkAction.jsp" method="post">
    			<input type="hidden" name="operation" value="<%=operation%>"/>
    			<% if (request.getParameter("idBookmark")!=null) {%>
    			<input type="hidden" name="idBookmark" value="<%=request.getParameter("idBookmark")%>"/>
    			<%} %>
				Name:
				<br />
				<input name="name" value="<%=name%>" type="text" ></input>
				<br />
				<br />
				URL:
				<br />
				<input name="url" value="<%=url%>" type="text"  ></input>
				<br />
				<br />
				Description:
				<br />
				<input name="description" value="<%=description%>" type="text" ></input>
				<br />
				<br />
				Tags:
				<br />
				<textarea name="tags" cols="50" rows="10"><%=tagsString%></textarea>
				<br />
<%
	if (operation.equals("share")){
		Collection<Community> communities=communityMgr.findCommunitiesByIdUser(session.getAttribute("idUser").toString());
		for (Community community : communities){
			out.println(community.getName()+"<input type=\"checkbox\" name=\"community"+community.getId()+"\" value=\""+community.getId()+"\" /><br/>");
		}
	}
%>
				<input name="send" value="send" type="submit" />
				<input name="cancel" type="button" value="Cancel" onclick="history.back()"/>
			</form>

		</div>
		<!-- End Main -->
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
        	</div>
        	<div class="clear"></div>
        	<div id="footer"></div>
		</div>
	</div>
</div>
</body>
</html>
