<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List shared bookmark</title>
</head>
<body>

<s:if test="bookmarks.size()==0">
    Doesn't exits bookmarks shared ... You must be the first 
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
					<s:url id="addComment" action="findComment" namespace="/comment"><s:param name="idBookmark" value="id" /></s:url>
					<s:a href="%{addComment}" cssClass="addcomment">Add Comment</s:a>
        		</div>
        		<div>
        			view comments
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