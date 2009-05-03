<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Edit Comment</title>
</head>
<body>


<s:form namespace="/comment" action="updateComment" method="post" >
	
	<s:hidden name="idBookmark" />

	<s:textarea label="Comment" name="text" cols="25" rows="5" />

    <s:if test="idComment==null">
        <s:submit label="Register" value="Register" />
    </s:if>
    <s:else>
        <s:submit label="Update" value="Update" />
    </s:else>

</s:form>

</body>
</html>