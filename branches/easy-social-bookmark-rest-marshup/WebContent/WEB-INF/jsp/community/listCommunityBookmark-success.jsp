<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List bookmarks of community</title>
</head>
<body>

<s:if test="bookmarks.size()==0">
    You don't have bookmarks in this community... 
</s:if>
<s:else>
    <s:iterator value="bookmarks">
        <div class="node">
			<h2 class="nodeTitle"><a href="<s:property id="url" value="url" />" target="_blank" ><s:property id="name" value="name" /></a></h2>
			<div class="post">
    			<div class="taxonomy">
    			Tag's: <s:property id="tags" value="tags" />
    			</div> 
				<div class="shared"></div>
				<div class="url"><a href="<s:property id="url" value="url" />" target="_blank"><s:property id="url" value="url" /></a></div>
				<div>
					<s:iterator value="comments" >
					<ul>
						<li><s:a cssClass="addcomment">(<s:property value="user.name" />)</s:a><s:property value="text" /></li>
					</ul>
					</s:iterator>
					<s:url id="addComment" action="findComment" namespace="/comment"><s:param name="idBookmark" value="id" /></s:url>
					<br/><s:a href="%{addComment}" >Add Comment</s:a>
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