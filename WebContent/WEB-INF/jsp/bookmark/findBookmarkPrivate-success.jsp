<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Bookmark Data</title>
</head>
<body>


<s:form id="bookmarkForm" name="bookmarkForm" namespace="/bookmark" action="updateBookmarkPrivate" method="post" >

    <s:hidden name="idBookmark" />
    
    <s:textfield id="name" label="Name" name="name" />
    
    <s:textfield id="url" label="URL" name="url" />

	<s:textarea id="description" label="Description" name="description" cols="25" rows="5" />
	
	<s:textarea id="tags" label="Tags" name="tags" cols="25" rows="5" />

    <s:if test="idBookmark==null">
        <s:submit label="Register" value="Register" />
    </s:if>
    <s:else>
        <s:submit label="Update" value="Update" />
    </s:else>

</s:form>

</body>
</html>