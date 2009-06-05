<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Login - You must be login to use this application</title>
</head>
<body>

<s:actionerror />

<s:url id="registerUser" action="findUser" namespace="/user" />
<s:a href="%{registerUser}">Register</s:a>
<br/>

<s:form id="logonForm" name="logonForm" action="logon" namespace="/" method="POST" >
    <s:textfield label="Login" name="login"/>
    <s:password label="Password" name="password"/>
    <s:submit type="submit" label="login" />
</s:form>

</body>
</html>