<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Decision Tree Parameters for Suggestion Community</title>
</head>
<body>
<% String path = application.getContextPath()+"/"; %>

<h2>Original Decision Tree</h2>
<img alt="original tree" src="<%=path %>original.gif" width="600px" >
<hr/>
<h1>PruneTree Decision Tree</h1>
<img alt="prune tree" src="<%=path %>prune.gif" width="600px">
<hr/>

<a href="<%=path %>prune.gif">view decision tree</a>

</body>
</html>