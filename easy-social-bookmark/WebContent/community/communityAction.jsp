<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.db.Database"%>
<%@page import="br.bookmark.db.DataBaseUtils"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta content="text/html; charset=UTF-8" http-equiv="content-type">
  <title>register</title>
</head>
<body>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit");
    CommunityMgr communityMgr=new CommunityMgr(bookmarkInit.getCommunityDAO(),bookmarkInit.getUserDAO());
    UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
    User user = userMgr.findById(""+session.getAttribute("idUser"));
    
    String idCommunity = request.getParameter("idCommunity");
    String operation = request.getParameter("operation");
    String msg = "";
    String href= "";
    
    if (operation.equals("removeMember")){
        communityMgr.deassignCommunity(idCommunity,request.getParameter("idUser"));
        msg = "member removed successfull";
        href = "managemembers.jsp?idCommunity="+idCommunity;
    }else if (operation.equals("managecommunity")){
    	
       Community community = communityMgr.findById(idCommunity);
       community.setDescription(request.getParameter("description"));
       community.setName(request.getParameter("name"));
       communityMgr.save(community);
       msg = "update done with success";
       href = "communityForm.jsp?community="+idCommunity;
    }else if(operation.equals("createcommunity")){
    	Community comm = new Community();
    	comm.setName(request.getParameter("name"));
    	comm.setDescription(request.getParameter("description"));
    	communityMgr.save(comm);
    	
        msg = "communitey created with success";
        href = "communityForm.jsp?community="+comm.getId();
    }else if (operation.equals("addcommunity")){
        Collection<Community> communities=communityMgr.findCommunities();
        for (Community community : communities){
            if (request.getParameter("community"+community.getId())!=null){
                communityMgr.assignCommunity(community.getId()+"",user.getId()+"");
            }
        }
        msg = "communitey add with success";
        href = "communityForm.jsp?addcommunity=addcommunity";
    }
    
%>

    <SCRIPT type="text/javascript">
    <%
    if(!msg.equals("")) out.print("alert('"+msg+"');"); 
    %>
        window.location.href = "<%=href%>";
    </SCRIPT>           

  </body>
</html>
