<?xml version="1.0" encoding="UTF-8"?><%@ taglib uri="/struts-tags" prefix="s" %>
<bookmarks>
	<comment id="<s:property value="id"/>">

	    <text><s:property value="description"/></text>
	    <bookmark><s:property value="idBookmark"/></bookmark>
	
		<user id="<s:property value="user.id"/>">
			<name><s:property value="user.name"/></name>
			<email><s:property value="user.email"/></email>
		</user>	
	</comment>
</bookmarks>