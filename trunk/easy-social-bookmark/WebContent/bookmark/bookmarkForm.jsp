<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.Init"%>
<%@page import="br.bookmark.db.BookmarkDAO"%>
<%@page import="br.bookmark.db.CommunityDAO"%>
<%@page import="br.bookmark.db.TagDAO"%>
<%@page import="br.bookmark.models.Bookmark"%>
<%@page import="java.util.*"%>
<%@taglib uri="br.bookmark.project" prefix="Widget"%>
<%


 	Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	CommunityDAO communityDAO = bookmarkInit.getCommunityDAO();
	BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
    TagDAO tagDAO = bookmarkInit.getTagDAO();

    
    String idUser = session.getAttribute("idUser").toString();
    
    String name="";
    String url="";
    String description="";
	String tagsString="";
	String operation="new"; 
    if (request.getParameter("idBookmark")!=null){
    	Bookmark bookmark=bookmarkDAO.findById(Long.parseLong(request.getParameter("idBookmark")));
    	name=bookmark.getName();
    	url=bookmark.getUrl();
    	description=bookmark.getDescription();
    	Collection<Tag> tags = tagDAO.findTagsByIdBookmark(Long.parseLong(request.getParameter("idBookmark")));
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Bookmark Form</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css"/>
</head>
<body>
<div id="wrap">
	<div id="container">
	
		<div id="header">
			<div id="caption">
				<div id="title">
					<h1>Easy Bookmark Social</h1>
					<p>Web System Development </p>
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
			<form name="BookmarkForm" action="BookmarkForm" method="post">
    			<input type="hidden" name="operation" value="<%=request.getParameter("operation")%>"/>
    			<% if (request.getParameter("idBookmark")!=null) {%>
    			<input type="hidden" name="idBookmark" value="<%=request.getParameter("idBookmark")%>"/>
    			<%} %>
				Name:
				<br />
				<input name="name" value="<%=request.getParameter("name")%>" type="text" ></input>
				<br />
				<br />
				URL:
				<br />
				<input name="url" value="<%=request.getParameter("url")%>" type="text"  ></input>
				<br />
				<br />
				Description:
				<br />
				<input name="description" value="<%=request.getParameter("description")%>" type="text" ></input>
				<br />
				<br />
				Tags:
				<br />
				<textarea name="tags" cols="50" rows="10"><%=request.getParameter("tagString")%></textarea>
				<br />
<%
	if (operation.equals("share")){
		Collection<Community> communities=communityDAO.findCommunitiesByIdUser(Long.parseLong(session.getAttribute("idUser")+""));
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
        	<div class="clear"></div>
        	<div id="footer"></div>
		</div>
	</div>
</div>
</body>
</html>


