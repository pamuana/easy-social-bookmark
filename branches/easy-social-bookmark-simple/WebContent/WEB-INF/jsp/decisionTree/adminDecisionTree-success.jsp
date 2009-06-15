<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Decision Tree Parameters for Suggestion Community</title>
</head>
<body>

<s:form action="processWebBookmark" namespace="/decisionTree" >

	<s:textarea id="sqlQueryDT" name="sqlQueryDT" value="" label="SQL Query"></s:textarea>
	<s:textfield id="entropy" name="entropy" value="0.5" label="Entropy" ></s:textfield>

	<s:submit value="submit" name="submit"></s:submit>
</s:form>

<s:a href="">view decision tree</s:a>

</body>
</html>