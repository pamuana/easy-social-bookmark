<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 

	BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
	TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
	String idUser = session.getAttribute("idUser").toString();
	
	String msg = "";
	String href= ""; href="bookmarkList.jsp";
	String operation=(request.getParameter("operation")!=null?request.getParameter("operation"):"");
	
	if (operation.equals("new")){
		Bookmark bookmark= new Bookmark();
		bookmark.setIdUser(Long.parseLong(idUser));
		bookmark.setName(request.getParameter("name"));
		bookmark.setUrl(request.getParameter("url"));
		bookmark.setDescription(request.getParameter("description"));
		bookmarkMgr.save(bookmark);
		
		String[] nameTags = request.getParameter("tags").split(",");
		Collection<Bookmark> userBookmarks=bookmarkMgr.findBookmarksByIdUser(idUser);
		for (Bookmark userBookmark:userBookmarks){
			if (userBookmark.getName().equals(bookmark.getName())&&userBookmark.getUrl().equals(bookmark.getUrl())){
				tagMgr.assignBookmarkWithIdUser(nameTags,""+userBookmark.getId(),idUser);
			}
		}
		
		msg="we save your Bookmark with success";
		
	} else if (operation.equals("edit")){
		Bookmark bookmark=bookmarkMgr.findById(request.getParameter("idBookmark"));
		bookmark.setName(request.getParameter("name"));
		bookmark.setUrl(request.getParameter("url"));
		bookmark.setDescription(request.getParameter("description"));
		bookmarkMgr.save(bookmark);
		
		String[] nameTags = request.getParameter("tags").split(",");
		Collection<String> collectionNameTags=new ArrayList<String>();
		for (String editNameTag:nameTags){
			collectionNameTags.add(editNameTag);
		}
		
		Collection<Tag> currentTags=tagMgr.findTagsByIdBookmark(""+bookmark.getId());
		for (Tag currentTag:currentTags){
			if (!collectionNameTags.contains(currentTag.getName())){
				tagMgr.deassignBookmark(""+currentTag.getId(),""+bookmark.getId());
			}
		}
		
		tagMgr.assignBookmarkWithIdUser(nameTags,""+bookmark.getId(),idUser);
		
		msg="we edit your Bookmark with success";
	}
%>
	<SCRIPT type="text/javascript">
	<% if(!msg.equals("")) out.print("alert('"+msg+"');"); %>
   		window.location.href = "<%=href%>";
	</SCRIPT>
</body>
</html>