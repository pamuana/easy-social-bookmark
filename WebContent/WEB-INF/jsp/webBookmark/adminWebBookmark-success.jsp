<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List of Options to Web Bookmark</title>
</head>
<body>

<s:form action="processWebBookmark" namespace="/webBookmark" >

	<s:textfield id="maxNumberUrls" name="maxNumberUrls" label="Max. Number. Url." ></s:textfield>
	<s:textfield id="maxDepth" name="maxDepth"  label="Max. Number Depth"></s:textfield>
	<s:textfield id="delayBetweenUrls" name="delayBetweenUrls"  label="Max. Delay Between Urls (ms)"></s:textfield>

<s:submit value="submit" name="submit"></s:submit>
</s:form>

</body>
</html>