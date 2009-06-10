<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>

<head>
    <title>Bookmark List</title>
</head>
<body>

<s:if test="results.size()==0">
    Doesn't exits bookmarks...
</s:if>
<s:else>
    <s:iterator id="next" value="results">
			<h3><s:property value="name" /></h3>
			<br/>options: <s:a href="%{id}">view</s:a>, <s:a href="%{id}?type=xml">xml</s:a>
			<br/>url: <s:property value="url" />
			<br/>description: <s:property value="description" />
			<br/>tags: <s:property value="tags" /> <br/>
		<hr/>
    </s:iterator>
</s:else>

<s:form method="post" action="%{#request.contextPath}/api/bookmark/">
    
    <s:textfield  label="Name" name="name"  />
    
    <s:textfield  label="URL" name="url" />

	<s:textarea label="Description" name="description" cols="25" rows="5" value="" />
	
	<s:textarea label="Tags" name="tags" cols="25" rows="5" value="" />

    <s:submit label="Register" value="Register" />

</s:form>

</body>
</html>