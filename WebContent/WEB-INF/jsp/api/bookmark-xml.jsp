<?xml version="1.0" encoding="UTF-8"?><%@ taglib uri="/struts-tags" prefix="s" %>
<bookmarks>
	<bookmark id="<s:property value="id"/>">
	    <name><s:property value="name"/></name>
	    <url><s:property value="url"/></url>
	    <description><s:property value="url"/></description>
	    <tags><s:property value="tags"/></tags>
	
		<user id="<s:property value="user.id"/>">
			<name><s:property value="user.name"/></name>
			<email><s:property value="user.email"/></email>
		</user>
	
	    <coments>
	    <s:iterator value="comments" >
	        <comment>
	            <text><s:property value="text" /></text>
	        </comment>
	    </s:iterator>
	    </coments>
	
	</bookmark>
</bookmarks>