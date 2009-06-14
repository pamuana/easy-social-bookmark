<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>List of my communities</title>
</head>
<body>

<s:iterator value="communities">
	<s:url id="unsubscribeCommunity" action="unsubscribeCommunity"
		namespace="/community">
		<s:param name="idCommunity" value="id" />
	</s:url>
	<s:property value="name" /> - <s:a href="%{unsubscribeCommunity}">unsubscribe</s:a>
	<br />
	<s:property value="description" />
	<br />
	<hr />
</s:iterator>

</body>
</html>