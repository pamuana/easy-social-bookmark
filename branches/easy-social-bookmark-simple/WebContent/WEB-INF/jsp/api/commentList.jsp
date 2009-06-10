<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>

<head>
    <title>Community List</title>
</head>
<body>

<s:if test="results.size()==0">
    Doesn't exits communities...
</s:if>
<s:else>
    <s:iterator id="next" value="results">
			<br/>options: <s:a href="%{id}">view</s:a>, <s:a href="%{id}?type=xml">xml</s:a>
			<br/>text: <s:property value="text" />
			<br/>description: <s:property value="description" />
		<hr/>
    </s:iterator>
</s:else>

<s:form method="post" action="%{#request.contextPath}/api/comment/">
    

	<s:textarea label="Text" name="text" cols="25" rows="5" value="" />

    <s:submit label="Register" value="Register" />

</s:form>

</body>
</html>