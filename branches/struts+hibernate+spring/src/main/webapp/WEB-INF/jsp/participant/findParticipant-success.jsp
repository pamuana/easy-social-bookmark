<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Add or Edit Participant</title>
</head>
<body>


<s:form namespace="/participant" action="updateParticipant" method="post" >

    <s:hidden name="idParticipant" />

	<s:textfield label="Role" name="role" />

    <s:if test="idCommunity==null">
        <s:submit label="Register" value="Register" />
    </s:if>
    <s:else>
        <s:submit label="Update" value="Update" />
    </s:else>

</s:form>

</body>
</html>