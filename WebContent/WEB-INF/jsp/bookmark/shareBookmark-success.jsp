<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Share Bookmark - Community List</title>
</head>
<body>


<s:form namespace="/bookmark" action="addCommunity" method="post" >

    <s:hidden name="idBookmark"/>    
    <s:select name="idCommunity" list="participants" listKey="community.id" listValue="community.name" size="5" >
    </s:select>

    <s:if test="idBookmark!=null">
		<s:submit label="Register" value="Register" />
	</s:if>
</s:form>

</body>
</html>