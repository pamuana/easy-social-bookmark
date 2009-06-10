<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>New Community</title>
</head>
<body>


<form action="updateCommunity" method="post" >

    <s:if test="#session['user']==null">
    	<s:property value="%{#session['user']}" />
    	<s:textfield name="user" value="%{#session['user']}" />
    </s:if>
    
    <s:textfield  label="Name" name="name" />

	<s:textarea label="Description" name="description" cols="25" rows="5" />

    <s:submit label="Register" value="Register" />

</form>

</body>
</html>