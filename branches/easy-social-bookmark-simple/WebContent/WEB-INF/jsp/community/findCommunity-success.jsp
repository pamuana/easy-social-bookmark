<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Edit Community</title>
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../js/cmxforms.js" type="text/javascript"></script>
<script src="../js/validate.js" type="text/javascript"></script>
</head>
<body>


<s:form id="communityForm" name="communtyForm" namespace="/community" action="updateCommunity" method="post" >

    <s:textfield id="name" label="Name" name="name" />

    <s:hidden name="idCommunity" />

	<s:textarea id="description" label="Description" name="description" cols="25" rows="5" />

    <s:if test="idCommunity==null">
    	<s:hidden name="idUser" value="%{#session['user'].id}" />
        <s:submit label="Register" value="Register" />
    </s:if>
    <s:else>
        <s:submit label="Update" value="Update" />
    </s:else>

</s:form>

</body>
</html>