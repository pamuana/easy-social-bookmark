<%@page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="br.bookmark.db.*"%>
<%@page import="java.util.*"%>
<%@taglib uri="br.bookmark.project" prefix="Widget"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    CommunityDAO communityDAO = bookmarkInit.getCommunityDAO();  
    MessageDAO messageDAO = bookmarkInit.getMessageDAO();
    String idCommunty=request.getParameter("idCommunity"); 
    String operation =request.getParameter("operation");
    
    String idUser=session.getAttribute("idUser").toString();
    Community community = communityDAO.findById(Long.parseLong(idCommunty));
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Message Form</title>
  <link rel="stylesheet" href="../css/style.css" type="text/css" />
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
<%
	if(operation.equals("new")){
%>
<form action="MessageNew">
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
		Message message = messageDAO.findById(Long.parseLong(idMessage));
%>
<form action="MessageEdit">
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
