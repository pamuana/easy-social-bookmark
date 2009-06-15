<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List comments</title>
</head>
<body>

<s:if test="comments.size()==0">
    You don't have Comments ... 
</s:if>
<s:else>
    <s:iterator id="comments" value="comments">
        <div class="node">
            <s:text name="Comment" />
            <br/>
            <br/>
	        <s:property value="text"/>
	        <br/>
	        <s:property value="data" />         
		</div>
		<p/>&nbsp;
		<p/>&nbsp;
    </s:iterator>
</s:else>

<s:reset label="Reset" onclick="history.back()"/>

</body>
</html>