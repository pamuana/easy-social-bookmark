<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List my bookmark Community</title>
</head>
<body>

<s:if test="bookmarks.size()==0">
    You don't have bookmarks ... in this community
</s:if>
<s:else>
    <s:iterator id="bookmarks" value="bookmarks">
        <div class="node">
			<h2 class="nodeTitle"><a href="<s:property id="url" value="url" />" target="_blank" ><s:property id="name" value="name" /></a></h2>
			<div class="post">
    			<div class="taxonomy">
    			Tag's:
    				<s:iterator id="tagsName" value="tagsUser" >
    					<s:property id="tagName" value="tag.name"/>,&nbsp;
    				</s:iterator>
    			</div> 
				<div class="shared"></div>
				<div class="url"><a href="<s:property id="url" value="url" />" target="_blank"><s:property id="url" value="url" /></a></div>
				<div>
					<s:if test="shared.equals('true')">
						<s:url id="unshareBookmark" action="unshareBookmark" namespace="/bookmark"><s:param name="idBookmark" value="id" /></s:url>
						<s:a href="%{unshareBookmark}" cssClass="addcomment">unshare</s:a>
					</s:if>
					<s:else>
						<s:url id="shareBookmark" action="shareBookmark" namespace="/bookmark"><s:param name="idBookmark" value="id" /></s:url>
						<s:a href="%{shareBookmark}" cssClass="addcomment">share</s:a>
					</s:else>
					<s:url id="editBookmark" action="findBookmark" namespace="/bookmark"><s:param name="idBookmark" value="id" /></s:url>
					<s:a href="%{editBookmark}" cssClass="editlinks">edit</s:a>
					<s:url id="deleteBookmark" action="deleteBookmark" namespace="/bookmark"><s:param name="idBookmark" value="id" /></s:url>
					<s:a href="%{deleteBookmark}">delete</s:a>
        		</div>
        		<div>
	        		<s:url id="addComment" action="findComment" namespace="/comment"><s:param name="idBookmark" value="id" /></s:url>
	        		<s:a href="%{addComment}" cssClass="addComment">edit</s:a>
	        		<s:url id="findSimilarInWebBookmark" action="findSimilarInWebBookmark" namespace="/bookmark"><s:param name="idBookmark" value="id" /></s:url>
					<s:a href="%{findSimilarInWebBookmark}">find similar in web</s:a>
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