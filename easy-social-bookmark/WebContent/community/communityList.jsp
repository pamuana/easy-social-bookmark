<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.db.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*" %>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
    CommunityDAO communityDAO = bookmarkInit.getCommunityDAO();
    TagDAO tagDAO = bookmarkInit.getTagDAO();
    CommentDAO commentDAO = bookmarkInit.getCommentDAO();
    MessageDAO messageDAO= bookmarkInit.getMessageDAO();
    
    String idUser=""+session.getAttribute("idUser");
    
    if(request.getParameter("deleteComm")!=null){
    	communityDAO.deleteCommunity(Long.parseLong(request.getParameter("deleteComm")));
    }
    
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="../css/style.css" type="text/css" />
<html xmlns="http://www.w3.org/1999/xhtml">
  <title>List Community</title>
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
		</div>
        <div id="content">
       	  <div id="main">
          <%
    if(!(communityDAO.findCommunitiesByIdUser(Long.parseLong(idUser)).size()==0)){
    for(Community comm : communityDAO.findCommunitiesByIdUser(Long.parseLong(idUser))){
%>
            <div class="node">
              <h2 class="nodeTitle">  <a class="namecommunity" href="<%= "communityMessage.jsp?idCommunity="+comm.getId() %>"><%= "Community: "+comm.getName() %></a></h2>
                <div ><%= "Description: "+comm.getDescription() %></div>
                <div class="post">
<%
		System.out.println(comm.getIdAdmin());
		System.out.println(Long.parseLong(idUser));
         if(comm.getIdAdmin()==Long.parseLong(idUser)){
%>
                  <a class="editlinks" href="<%= "communityForm.jsp?community="+comm.getId() %>">edit</a>                 
                  <a class="addcomment"href="<%= "managemembers.jsp?idCommunity="+comm.getId()%>">Manage Members</a>
                  <a href="<%= "communityList.jsp?deleteComm="+comm.getId() %>">delete</a>
<%
         }
%>         
                </div> 
                <p/>&nbsp;
                <hr/>
                <p/>&nbsp;
            </div>
<%
    }
    }else{
%>
                <div class="node">
                    There are no community to this user.
                    <br />
                    <div class="post">
                        <a href="communityForm.jsp?create=create">Cretate Community</a>
                        <a href="communityForm.jsp?addcommunity=addcommunity">Add Community</a>
                    </div>
                </div>
<%
    }
%>
          </div>
		  <div id="sidebar">
        		<div id="block-menu-principal" class="block">
        			<h2>Main Menu</h2>
        			<div class="content">
                        <ul>
                            <li><a title="view bookmark" href="../bookmark/bookmarkList.jsp">&raquo;&nbsp;View Bookmark</a></li>
                            <li><a title="new bookmark" href="../bookmark/bookmarkForm.jsp">&raquo;&nbsp;New Bookmark</a></li>
                            <li><a title="view interesting" href="#">&raquo;&nbsp;View Interesting</a></li>
                            <li><a title="view statistic" href="#">&raquo;&nbsp;View Statistics</a></li>
                        </ul>
						<br/><p/>&nbsp;
						<ul>
                            <li><a title="list communities" href="communityList.jsp">&raquo;&nbsp;List Communities</a></li>
                            <li><a title="new community" href="communityForm.jsp?create=create">&raquo;&nbsp;New Community</a></li>
                            <li><a title="add community" href="communityForm.jsp?addcommunity=addcommunity">&raquo;&nbsp;Add Community</a></li>
                        </ul>
    				</div>
                 </div>
                 <p/>&nbsp;
        		<hr/>
        		<p/>&nbsp;
 				<div id="block-menu-community" class="block">
                     <h2> <a title="list communities" href="communityList.jsp">List Communities</a></h2>
                     <div class="content">
                     <ul class="menu">
<%
        // TODO criar uma funç~ao recursiva para esta chamada
        Collection<Community> communities=communityDAO.findCommunitiesByIdUser(Long.parseLong(idUser));
        for (Community community:communities){
            if (community.getIdParent()==0){
%>
                <li><a href="#"><%=community.getName()%></a></li>
                    <ul>
<%
                        Collection<Community> subcommunities=communityDAO.findByIdParent(community.getId());
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
        	<div class="clear"></div>
        	<div id="footer"></div>
          </div>
	</div>
</div>
</body>
</html>
