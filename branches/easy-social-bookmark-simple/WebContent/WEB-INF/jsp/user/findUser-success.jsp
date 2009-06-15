<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>User Data</title>
    
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../js/cmxforms.js" type="text/javascript"></script>
<script src="../js/validate.js" type="text/javascript"></script>
</head>
<body>

<s:if test="exception!=null" >
	E-mail address exists. Use another email address.
</s:if>

<s:form id="userForm" name="userForm" namespace="/user" action="updateUser" method="post" enctype="multipart/form-data"  cssClass="cmxform" >

    <s:textfield  id="name" cssClass="required cname" label="Name" name="name"  />
    <s:textfield  cssClass="required number"  maxlength="2" id="age" label="Age" name="age"  />
    <s:textfield  cssClass="required email" id="email" label="E-Mail" name="email"   />
    <s:textfield  cssClass="required" id="address" label="Address" name="address"   />
    <s:textfield  cssClass="required" id="city" label="City" name="City"  />
    <s:textfield  cssClass="required" id="state" label="State" name="state"  />
    <s:textfield  cssClass="required" id="country" label="Country" name="country"  />
    <s:textfield  id="login" label="Login" name="login"  />    
    <s:password cssClass="required password" id="password" label="Password" name="password"  />
    <s:password id="confirm_password" label="Confirm Password" name="confirm_password"  />

    <s:hidden name="idUser" />
    <s:hidden name="role" value="user"/>

    <s:if test="idUser==null">
        <s:submit label="Register" value="Register" />
    </s:if>
    <s:else>
        <s:submit label="Update" value="Update" />
    </s:else>

</s:form>

</body>
</html>