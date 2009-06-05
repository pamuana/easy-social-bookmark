<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List my bookmark</title>
</head>
<body>

<s:if test="bookmarks.size()==0">
    You don't have bookmarks ... 
</s:if>
<s:else>
    <s:iterator id="bookmarks" value="bookmarks">
        <div class="node">
			<h2 class="nodeTitle"><a href="<s:property id="url" value="url" />" target="_blank" ><s:property id="name" value="name" /></a></h2>
			<div class="post">
    			<div class="taxonomy">
    			Tag's: <s:property id="tags" value="tags" />
    			</div> 
				<div class="shared"></div>
				<div class="url"><a href="<s:property id="url" value="url" />" target="_blank"><s:property id="url" value="url" /></a></div>
				<div>
					<s:url id="shareBookmark" action="shareBookmark" namespace="/bookmark"><s:param name="idBookmark" value="id" /></s:url>
					<s:a href="%{shareBookmark}" cssClass="addcomment">share</s:a>
					<s:url id="editBookmark" action="findBookmark" namespace="/bookmark"><s:param name="idBookmark" value="id" /></s:url>
					<s:a href="%{editBookmark}" cssClass="editlinks">edit</s:a>
					<s:url id="deleteBookmark" action="deleteBookmark" namespace="/bookmark"><s:param name="idBookmark" value="id" /></s:url>
					<s:a href="%{deleteBookmark}">delete</s:a>
        		</div>
        	</div>
		</div>
		<p/>&nbsp;
		<hr/>
		<p/>&nbsp;
    </s:iterator>
</s:else>

</body>
</html>