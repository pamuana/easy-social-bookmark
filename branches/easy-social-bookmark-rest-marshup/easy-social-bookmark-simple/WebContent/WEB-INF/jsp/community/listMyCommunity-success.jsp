<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List of my communities</title>
</head>
<body>

	<s:iterator value="#session['user'].participants">
		(<s:property value="role" />)
		<s:if test="role.toString()=='ADMIN'" >
			<s:url id="editCommunity" action="findCommunity" namespace="/community"><s:param name="idCommunity" value="community.id" /></s:url>
			<s:url id="deleteCommunity" action="deleteCommunity" namespace="/community"><s:param name="idCommunity" value="community.id" /></s:url>
			<s:property value="community.name" /> - <s:a href="%{editCommunity}" cssClass="addcomment">edit</s:a> - <s:a href="%{deleteCommunity}">delete</s:a>
		</s:if>
		<s:else>
			<s:url id="unsubscribeCommunity" action="unsubscribeCommunity" namespace="/community"><s:param name="idCommunity" value="community.id" /></s:url> 
			<s:property value="community.name" /> - <s:a href="%{unsubscribeCommunity}" cssClass="addcomment">unsubscribe</s:a>
		</s:else>
		<br/>
		<s:property value="community.description"/>
		<br/>
		<hr/>
	</s:iterator>

</body>
</html>