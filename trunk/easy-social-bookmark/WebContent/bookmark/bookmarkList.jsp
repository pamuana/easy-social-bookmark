<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="UTF-8"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<%
    Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
	BookmarkMgr bookmarkMgr = new BookmarkMgr(bookmarkInit.getBookmarkDAO());
	TagMgr tagMgr = new TagMgr(bookmarkInit.getTagDAO());
	UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
	User user = userMgr.findById(""+session.getAttribute("idUser"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html template="true">
<head>
  <meta content="text/html; charset=ISO-8859-1"
 http-equiv="content-type">
  <title>View Bookmarks</title>
  <style type="text/css">
.tree { overflow: auto; width: 15em; height: 25em; cursor: default; border: 1px solid gray; padding-left: .5em;}
.treeitem { margin-right: .6em;}
.collapsedgroup { display : none; }
.group { margin-left: 1.5em; }
.treeitemfocus { outline: 0px; background-color: #dddddd; color: black;}
  </style>
  <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div style="text-align: left; width: 150px;">
<div class="menuheader">Main
Menu</div>

<!-- begin left menu -->
<div id="LeftMNav">
<ul>
  <li><a title="new bookmark" href="bookmarkForm.jsp">&raquo;&nbsp;New Bookmark</a></li>
  <li><a title="Forum Help/Guide" href="http://www.ex-designz.net/faqhelp.asp">&raquo;&nbsp;Forum Help</a></li>
  <li><a title="Browse through our hundreds of free articles"
 href="http://www.ex-designz.net/article.asp">&raquo;&nbsp;Article
Directory</a></li>
  <li><a title="Free sing-along lyrics with tune"
 href="http://www.ex-designz.net/lyricskaraoke.asp">&raquo;&nbsp;Music</a></li>
  <li><a title="Prank,Filipino,computer and cebuano jokes"
 href="http://www.ex-designz.net/extremejoke.asp">&raquo;&nbsp;Jokes</a></li>
  <li><a title="Photos of sexy H-wood beauties,hunks and Pinay babes"
 href="http://www.ex-designz.net/gallery/default.asp">&raquo;&nbsp;Photo
Gallery</a></li>
  <li><a title="Download freeware,midi and web template"
 href="http://www.ex-designz.net/dlcentral.asp">&raquo;&nbsp;Download
Central</a></li>
  <li><a title="Link Directory"
 href="http://www.ex-designz.net/directory/default.asp">&raquo;&nbsp;Link
Directory</a></li>
  <li><a title="Recipe" href="http://www.ex-designz.net/recipemain.asp">&raquo;&nbsp;World
Recipe</a></li>
  <li><a title="Find,make friends at Ex-designz dating service"
 href="http://www.ex-designz.net/dating.asp">&raquo;&nbsp;Online
Dating</a></li>
  <li><a title="Horoscope-Astrology"
 href="http://www.ex-designz.net/horoscope.asp">&raquo;&nbsp;Horoscope</a></li>
  <li><a title="Headline News from around the world"
 href="http://www.ex-designz.net/topstorynews.asp">&raquo;&nbsp;Headline
News</a></li>
</ul>
</div>
<!-- end left menu -->

<div id="tree" role="tree" tabindex="-1" class="tree"
 onclick="return treeItemClick(event);"
 ondblclick="return treeItemEvent(event);"
 onkeydown="return treeItemEvent(event);"
 onkeypress="return treeItemEvent(event);">
<div id="veggieDiv" role="presentation"><img src="minus.gif"
 tabindex="-1" role="presentation" onclick="imgToggle(event);" alt=""><span
 tabindex="0" role="treeitem" aria-expanded="true" class="treeitem"
 id="veggie" onfocus="this.className='treeitemfocus';"
 onblur="this.className='treeitem';">Veggies</span></div>
<div role="group" class="group" id="veggieGroup">
<div id="greenPar" role="presentation"><img src="minus.gif"
 tabindex="-1" role="presentation" onclick="imgToggle(event);" alt=""><span
 tabindex="-1" role="treeitem" aria-expanded="true" class="treeitem">Green</span></div>
<div role="group" class="group" id="greenGroup">
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Asparagus</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Kale</span></div>
<div role="presentation">
<img src="minus.gif" tabindex="-1" role="presentation"
 onclick="imgToggle(event);" alt=""><span tabindex="-1" role="treeitem"
 aria-expanded="true" class="treeitem">Leafy</span>
</div>
<div role="group" class="group">
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Lettuce</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Kale</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Spinach</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Chard</span></div>
</div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Green
beans</span></div>
</div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Legumes</span></div>
<div role="presentation">
<img src="minus.gif" tabindex="-1" role="presentation"
 onclick="imgToggle(event);" alt=""><span tabindex="-1" role="treeitem"
 aria-expanded="true" class="treeitem">Yellow</span>
</div>
<div role="group" class="group">
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Bell
peppers</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Squash</span></div>
</div>
</div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Junk
food</span></div>
<div role="presentation"><img src="minus.gif" tabindex="-1"
 role="presentation" onclick="imgToggle(event);" alt=""><span
 tabindex="-1" role="treeitem" class="treeitem" aria-expanded="true">Fruit</span></div>
<div role="group" class="group">
<div id="typicalDiv" role="presentation"><img src="minus.gif"
 tabindex="-1" role="presentation" onclick="imgToggle(event);" alt=""><span
 tabindex="-1" role="treeitem" class="treeitem" aria-expanded="true">Typical</span></div>
<div role="group" class="group" id="typicalGroup">
<div role="presentation" id="orange"><span tabindex="-1" role="treeitem"
 class="treeitem">Oranges</span></div>
<div role="presentation" id="apple"><span tabindex="-1" role="treeitem"
 class="treeitem">Apples</span></div>
<div role="presentation" id="banana"><span tabindex="-1" role="treeitem"
 class="treeitem">Bananas</span></div>
<div role="presentation" id="pine"><span tabindex="-1" role="treeitem"
 class="treeitem">Pineapple</span></div>
<div role="presentation" id="grape"><span tabindex="-1" role="treeitem"
 class="treeitem">Grapes</span></div>
</div>
<div id="exoiticDiv" role="presentation"><img src="minus.gif"
 tabindex="-1" role="presentation" onclick="imgToggle(event);" alt=""><span
 tabindex="-1" role="treeitem" class="treeitem" aria-expanded="true">Exotic</span></div>
<div role="group" class="group" id="exoticGroup">
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Mangos</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Guava</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Star
fruit</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Coconut</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Bread
fruit</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem">Custard
apples</span></div>
<div role="presentation"><span tabindex="-1" role="treeitem"
 class="treeitem" id="end item">Papaya</span></div>
</div>
</div>
</div>
	<div id="center">
	    <div class="bookmark">
<%
	
		for (Bookmark bm : bookmarkMgr.findBookmarksByIdUser(user.getId()+"")) {
%>
		    <div class="name"><%=bm.getName()%></div>
		    <div class="shared"><%= bookmarkMgr.findByUrl(bm.getUrl()).size() %></div>
		    <div class="url"><%=bm.getUrl()%></div>
		    <div class="commands"><a href="bookmarkForm.jsp?operation=share&idBookmark=<%=bm.getId()%>">share</a>,<a href="bookmarkForm.jsp?idBookmark=<%=bm.getId()%>">edit</a>,<a href="bookmarkAction.jsp?operation=delete&idBookmark=<%= bm.getId() %>">delete</a></div>
<%
		    for (Tag tag : tagMgr.findTagsByIdBookmark(bm.getId()+"")) {
%>
	        <div class="tags"><%=tag.getName()%></div>
<%
		    }
		}
%>
	    </div>
	</div>
</div>
</body>
</html>
