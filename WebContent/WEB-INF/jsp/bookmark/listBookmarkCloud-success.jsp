<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List shared bookmark cloud</title>
</head>
<body>

<s:if test="bookmarks.size()==0">
    Doesn't exits shared bookmarks cloud... You must be the first 
</s:if>
<s:else>
    
        <div class="node">
			<div class="post">
        		<div>
        		  <h2>Shared Tags Cloud</h2>
                <div >
                <%=(session.getAttribute("cloudShared").toString()!=null?session.getAttribute("cloudShared").toString():"")%>
                </div>
        		</div>
        	</div>
		</div>
		<p/>&nbsp;


</s:else>

</body>
</html>