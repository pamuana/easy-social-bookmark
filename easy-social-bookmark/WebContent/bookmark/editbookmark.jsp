<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
    TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
    Bookmark bookmark = bookmarkMgr.findById(request.getParameter("bookmark")+"");
    Tag tag = tagMgr.findById(""+session.getAttribute("idUser"));
    UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
    User user = userMgr.findById(""+session.getAttribute("idUser"));
    
    if(request.getParameter("edit")!=null){
        bookmark.setName(request.getParameter("name")+"");
        bookmark.setDescription(request.getParameter("description")+"");
        bookmark.setUrl(request.getParameter("url")+"");
        String tags = request.getParameter("tags")+"";
        
        for(String t : tags.split(",")){
            
             for(Tag tagIteration : tagMgr.findTagsByIdBookmark(bookmark.getId()+"")){
                 if(!tagIteration.getName().equals(t)){
                     for(Tag tagIteration2 : tagMgr.findTagsByIdUser(user.getId()+"") ){    
                         if(!tagIteration2.getName().equals(t)){
                             Tag tagNew = new Tag();
                             tagNew.setName(t);
                             tagNew.setIdUser(Long.parseLong(user.getId()+""));
                             tagMgr.save(tagNew); 
                         }else{
                             tagMgr.assignBookmark(tagIteration2.getId()+"",bookmark.getId()+"");
                         }
                     }
                 }
             }
        }
        
    }
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type" />
  <title>Edit bookmark</title>


</head>

<body>
<form name="editbookmark" action="editbookmark.jsp" id="edit" method="post">
    <input type="hidden" name="edit" value="edit"/>
    Name:
    <br />
    <input name="name" value="<%= bookmark.getName() %>" type="text" ></input>
    <br />
    <br />
    URL:
    <br />
    <input name="url" value="<%= bookmark.getUrl() %>" type="text"  ></input>
    <br />
    <br />
    Descption:
    <br />
    <input name="description" value="<%= bookmark.getDescription() %>" type="text" ></input>
    <br />
    <br />
    Tags:
    <br />
    <textarea name="tags" cols="50" rows="10">
<%
    for(Tag iteTag :  tagMgr.findTagsByIdBookmark(bookmark.getId()+"")){
%>
    <%= iteTag.getName() %>,
<%
    }
%>
    </textarea>
    <br />
    <input name="send" value="send" type="submit" />
    <input name="cancel" type="button" value="Cancel"/>
</form>

</body></html>