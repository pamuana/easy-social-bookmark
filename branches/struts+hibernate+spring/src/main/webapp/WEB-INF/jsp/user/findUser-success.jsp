<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="user.findUser.title" /></title>
</head>
<body>

<s:if test="exception!=null" >
    <s:text name="info.emailAddressExists" />
</s:if>

<s:form namespace="/user" action="updateUser" method="post" enctype="multipart/form-data" >

    <s:textfield  label="Name" name="name" />
    <s:textfield  label="Login" name="login" />
    <s:textfield  label="E-Mail" name="email" />
    <s:password label="Password" name="password" />

    <s:hidden name="idUser" />

    <s:if test="idUser==null">
        <s:submit label="Register" value="Register" />
    </s:if>
    <s:else>
        <s:submit label="Update" value="Update" />
    </s:else>

</s:form>

</body>
</html>