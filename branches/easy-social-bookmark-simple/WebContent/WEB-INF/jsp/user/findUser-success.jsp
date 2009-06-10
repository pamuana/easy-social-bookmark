<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>User Data</title>
</head>
<body>

<s:if test="exception!=null" >
	E-mail address exists. Use another email address.
</s:if>

<s:form id="userForm" name="userForm" namespace="/user" action="updateUser" method="post" enctype="multipart/form-data"  cssClass="cmxform" >

    <s:textfield  id="name" label="Name" name="name"  />
    <s:textfield  id="login" label="Login" name="login"  />
    <s:textfield  id="email" label="E-Mail" name="email"   />
    <s:password id="password" label="Password" name="password"  />
    <s:password id="confirm_password" label="Confirm Password" name="confirm_password"  />

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