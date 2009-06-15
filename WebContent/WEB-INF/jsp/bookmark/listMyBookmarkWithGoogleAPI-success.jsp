<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>List my bookmark - Search engine for tags</title>
        <style type="text/css">

      a span.tagline:hover {
        color : rgb(204, 0, 0);
        border-bottom : 2px solid rgb(204, 0, 0);
      }

      .gsc-control { width : 100% }
      #searchResults form.gsc-search-box { display : none; }
      #branding {
        background-color : #f9f9f9;
        padding-top : 10px;
        width : 100%;
      }

      #searchResults .gsc-control {
        width : 100%;
      }
      /* to right align branding, do this
      #branding table.gsc-branding { width : auto; }
      #branding td.gsc-branding-text div.gsc-branding-text { text-align : left; }
      */

    </style>
    <script src="http://www.google.com/jsapi" type="text/javascript"></script>
    <script language="Javascript" type="text/javascript">
    //<![CDATA[
      google.load('search', '1');
      var searchControl;
      function OnLoad() {

        //google.search.Search.getBranding(document.getElementById("branding"));

        // register sites
        var bs = new google.search.BlogSearch();
        bs.setUserDefinedLabel("blogosphere");

        var amazon = new google.search.WebSearch();
        amazon.setUserDefinedLabel("amazon.com");
        amazon.setSiteRestriction("amazon.com");

        var ws = new google.search.WebSearch();
        ws.setUserDefinedLabel("web");

        searchControl = new google.search.SearchControl();
        searchControl.addSearcher(ws);
        searchControl.addSearcher(bs);
        searchControl.addSearcher(amazon);

        var options = new google.search.DrawOptions();
        options.setDrawMode(GSearchControl.DRAW_MODE_TABBED);
        searchControl.draw(document.getElementById("searchResults"), options);
      }

      google.setOnLoadCallback(OnLoad, true);
      
    //]]>
    </script>
</head>
<body>
<div id="branding" class="hide" >This is the section for similar URLs ... </div>
<s:div id="searchResults"></s:div>
<s:if test="bookmarks.size()==0">
    You don't have bookmarks ... 
</s:if>
<s:else>
	<hr/>
    <s:iterator id="bookmarks" value="bookmarks">
        <div class="node">
			<h2 class="nodeTitle"><a href="<s:property id="url" value="url" />" target="_blank" ><s:property id="name" value="name" /></a></h2>
			<div class="post">
    			<div class="taxonomy">
    			Tag's:
    				<s:iterator id="tagsName" value="tagsUser" >
    					<s:property id="tagName" value="tag.name"/>,&nbsp;
    				</s:iterator>
    			</div> 
				<div class="shared"></div>
				<div class="url"><a href="<s:property id="url" value="url" />" target="_blank"><s:property id="url" value="url" /></a></div>
				<div>
					<s:a href="javascript:searchControl.execute('%{href}')" cssClass="addcomment">view similar for tag's</s:a>
        		</div>
        	</div>
		</div>
		<p/>&nbsp;
		<hr/>
		<p/>&nbsp;
    </s:iterator>
</s:else>

</body>
</html>