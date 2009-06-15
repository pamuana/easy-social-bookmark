<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java"  import="java.util.*" %>
<html>
<head>
	<title>Easy Social Bookmark</title>
    <link href="<s:url value='/css/style.css'/>" rel="stylesheet" type="text/css" media="all"/>
	<script src="<s:url value='/js/include.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jquery.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/jquery.validate.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/cmxforms.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/js/validate.js'/>" type="text/javascript"></script>
    <decorator:head/>
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
				<div id="searchbox">
				<s:if test="#session['user']!=null">
					<s:url id="logoff" action="logoff" namespace="/" />
					<s:a href="%{logoff}">Logoff</s:a> -
					<s:url id="editPerfil" action="findUser" namespace="/user" ><s:param name="idUser" value="#session['user'].id" /></s:url>
					<s:a href="%{editPerfil}">Edit Perfil</s:a>
				</s:if>
				</div>
			</div>
		</div>
		<div id="content">
			<div id="main">
          		<decorator:body/>			
				<br />
	            <p><br />
	              <br />
	            </p>
	            <p>&nbsp;</p>
	            <p>&nbsp;</p>
	            <p>&nbsp;</p>
	            <p>&nbsp;</p>
	            <p>&nbsp;</p>
	            <p>&nbsp;</p>
	            <p><br />
	            	<br />
            	</p>
			</div>
        <div id="sidebar">
		<div id="block-menu-principal" class="block">
			<h2>View Bookmarks</h2>
			<s:url id="listBookmark" action="listBookmark" namespace="/" ><s:param name="tag" value="" /></s:url>
			<s:url id="listMyBookmark" action="listMyBookmark" namespace="/bookmark" ><s:param name="tag" value="" /></s:url>
			<s:url id="addBookmark" action="findBookmark" namespace="/bookmark" />
			<s:url id="listMyBookmarkWithGoogleAPI" action="listMyBookmarkWithGoogleAPI" namespace="/bookmark" />
			<div class="content">
				<ul>
					<li><s:a href="%{listBookmark}">List shared bookmarks</s:a></li>
					<li><s:a href="%{listMyBookmark}">List my bookmarks</s:a></li>
					<li><s:a href="%{addBookmark}">Add bookmark</s:a></li>
					<li><s:a href="%{listMyBookmarkWithGoogleAPI}">List bookmark - Mashup</s:a></li>
				</ul>
				<br/>Bookmarks in your community:<br/>
				<ul>
					<%=(session.getAttribute("communityListText").toString()!=null ? session.getAttribute("communityListText").toString() : "") %>
				</ul>
			</div>
		</div>
		<p/>&nbsp;
		<hr/>
		<p/>&nbsp;
		<s:if test="#session['user']!=null">
			<div id="block-menu-community" class="block">
				<h2>Management Communities</h2>
				<s:url id="listMyCommunity" action="listMyCommunity" namespace="/community" />
				<s:url id="addCommunity" action="findCommunity" namespace="/community" ><s:param name="idCommunity" value="" /> </s:url>
				<s:url id="listSubscribeCommunity" action="listSubscribeCommunity" namespace="/community" />
				<div class="content">
					<ul class="menu">
						<li><s:a href="%{listMyCommunity}">List my communities</s:a></li>
						<li><s:a href="%{addCommunity}">Add community</s:a></li>
						<li><s:a href="%{listSubscribeCommunity}">Subscribe community</s:a></li>
					</ul>
				</div>
			</div>
			<p/>&nbsp;
			<hr/>
			<p/>&nbsp;
		</s:if>
		<p/>&nbsp;
		<s:if test="#session['user']!=null">
			<div id="block-menu-community" class="block">
				<h2>Administrator Functions</h2>
				<s:url id="adminWebBookmark" action="adminWebBookmark" namespace="/webBookmark" />
				<s:url id="adminDecisionTree" action="adminDecisionTree" namespace="/decisionTree" ></s:url>
				<s:url id="listSubscribeCommunity" action="listSubscribeCommunity" namespace="/community" />
				<div class="content">
					<ul class="menu">
						<li><s:a href="%{adminWebBookmark}">Web Bookmark</s:a></li>
						<li><s:a href="%{adminDecisionTree}">D. T for Community Suggestion. </s:a></li>
					</ul>
				</div>
			</div>
			<p/>&nbsp;
			<hr/>
			<p/>&nbsp;
		</s:if>
		<p/>&nbsp;
			<div id="block-tags" class="block">
				<h2>Tags</h2>
				<div class="content">
				<%=(session.getAttribute("cloudText").toString()!=null?session.getAttribute("cloudText").toString():"")%>
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
