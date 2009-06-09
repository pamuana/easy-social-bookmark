<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>New Comment</title>
</head>
<body>


<form action="updateComment" method="post" >
<input type="hidden" name="__http_method" value="delete" />

    <s:if test="#session['user']==null">
    	<s:property value="%{#session['user']}" />
    	<s:textfield name="user" value="%{#session['user']}" />
    </s:if>
    

	<s:textarea label="Text" name="text" cols="25" rows="5" />

    <s:submit label="Register" value="Register" />

</form>

</body>
</html>