<?xml version="1.0" encoding="UTF-8"?><%@ taglib uri="/struts-tags" prefix="s" %>
<bookmarks>
	<community id="<s:property value="id"/>">
	    <name><s:property value="name"/></name>
	    <description><s:property value="description"/></description>
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
	
	</community>
</bookmarks>