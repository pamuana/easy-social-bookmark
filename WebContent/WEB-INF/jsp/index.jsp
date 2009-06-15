<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Welcome to Easy Social Bookmark</title>
</head>
<body>

<s:actionerror />

<s:if test="#session['user']==null">
<s:url id="registerUser" action="findUser" namespace="/user" />
<s:a href="%{registerUser}">Register</s:a>
<br/>

<s:form action="logon" namespace="/" method="POST" >
    <s:textfield label="Login" name="login"/>
    <s:password label="Password" name="password"/>
    <s:submit type="submit" label="login" />
</s:form>
</s:if>
<s:else>
	Welcome to system ...
	
	we suggest
	<hr/>
	<s:iterator value="communities">
		<p/>
			<s:url id="subscribeCommunity" action="subscribeCommunity" namespace="/community"><s:param name="idCommunity" value="id" /></s:url> 
			<s:property value="name" /> - <s:a href="%{subscribeCommunity}" >subscribe</s:a>
			<br/>
			<s:property value="description"/>
			<br/>
			<hr/>
	</s:iterator>
</s:else>
</body>

</html>