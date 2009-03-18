<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    CommunityMgr communityMgr = new CommunityMgr(bookmarkInit.getCommunityDAO(), bookmarkInit.getUserDAO());  
    MessageMgr messageMgr = new MessageMgr(bookmarkInit.getMessageDAO());
    String idCommunty=request.getParameter("idCommunity"); 
    String operation =request.getParameter("operation");
    
    String idUser=session.getAttribute("idUser").toString();
    Community community = communityMgr.findById(idCommunty);
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
  <title>Message Form</title>
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
	if(operation.equals("new")){
%>
<form action="messageAction.jsp">
<input type="hidden" name="idCommunity" value="<%= idCommunty %>"/>
<input type="hidden" name="operation" value="<%= operation %>"/>
<div>Community <%= community.getName() %></div>
<div class="message">
Message:
<br/>
<textarea rows="10" cols="50" name="message" ></textarea>
<br/>
<input type="submit" value="send" name="send" />
<input type="button" value="Cancel" name="cancel" onclick="history.back()" />
</div>
</form>
<%
	}else if(operation.equals("edit")){
		String idMessage =request.getParameter("idMessage");
		Message message = messageMgr.findById(idMessage);
%>
<form action="messageAction.jsp">
<input type="hidden" name="idCommunity" value="<%= idCommunty %>"/>
<input type="hidden" name="operation" value="<%= operation %>"/>
<div>Community <%= community.getName() %></div>
<div class="message">
Message:
<br/>
<textarea rows="10" cols="50" name="message" ><%= message.getText() %></textarea>
<br/>
<input type="submit" value="send" name="send" />
<input type="button" value="Cancel" name="cancel" onclick="history.back()"/>
</div>
</form>
<%
    }
%>

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
				Collection<Community> myCommunities=communityMgr.findCommunitiesByIdUser(idUser);
				for (Community myCommunity:myCommunities){
					if (myCommunity.getIdParent()==0){
%>
						<li><a href="bookmarkCommunityList.jsp?idCommunity=<%=myCommunity.getId()%>"><%=myCommunity.getName()%></a>
						<ul>
<%
						Collection<Community> subcommunities=communityMgr.findSubCommunity(""+myCommunity.getId());
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
