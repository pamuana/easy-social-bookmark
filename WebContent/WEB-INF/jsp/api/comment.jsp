<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Comment</title>
</head>
<body>

<table>
    <tr><td colspan="2"><h1><s:property value="name"/></h1></td></tr>
    <tr>
        <td>text:</td>
        <td><s:property value="description"/></td>
    </tr>
</table>

	<form method="post">
        <input type="hidden" name="__http_method" value="delete" />
		<input type="submit" value="Delete" />
    </form>

</body>
</html>