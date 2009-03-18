<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    MessageMgr messageMgr= new MessageMgr(bookmarkInit.getMessageDAO());    
    String idUser=""+session.getAttribute("idUser");  
    String idCommunty=request.getParameter("idCommunity");
    String operation = request.getParameter("operation");
    String sendback = "";
    String href= "";
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Message Action</title>
</head>
<body>
<%
	if(operation.equals("new")){
	    String message = request.getParameter("message");
		Message msg = new Message();
		msg.setIdCommunity(Long.parseLong(idCommunty));
		msg.setIdUser(Long.parseLong(idUser));
		msg.setText(message);
		messageMgr.save(msg);
		sendback = "Message created with success";
		href = "communityMessage.jsp?idCommunity="+idCommunty;
	}else if(operation.equals("edit")){
		String message = request.getParameter("message");
        Message msg = messageMgr.findById(idUser);
        msg.setIdCommunity(Long.parseLong(idCommunty));
        msg.setIdUser(Long.parseLong(idUser));
        msg.setText(message);
        messageMgr.save(msg);
        sendback = "Message updated with success";
        href = "communityMessage.jsp?idCommunity="+idCommunty;
		
	}else if(operation.equals("delete")){
		String idMessage = request.getParameter("idMessage");
		messageMgr.delete(Long.parseLong(idMessage));
		sendback = "Message deleted with success";
        href = "communityMessage.jsp?idCommunity="+idCommunty;
	}

%>
<SCRIPT type="text/javascript">
<%
    if(!sendback.equals("")) out.print("alert('"+sendback+"');"); 
 %>
        window.location.href = "<%=href%>";
</SCRIPT>           


</body>
</html>