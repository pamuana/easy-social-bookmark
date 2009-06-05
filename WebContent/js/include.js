var AJAX = createXMLHttpRequest();
var AJAX2 = createXMLHttpRequest();

function createXMLHttpRequest(){
  // See http://en.wikipedia.org/wiki/XMLHttpRequest
  // Provide the XMLHttpRequest class for IE 5.x-6.x:
  if( typeof XMLHttpRequest == "undefined" ) XMLHttpRequest = function() {
    try { return new ActiveXObject("Msxml2.XMLHTTP.6.0") } catch(e) {}
    try { return new ActiveXObject("Msxml2.XMLHTTP.3.0") } catch(e) {}
    try { return new ActiveXObject("Msxml2.XMLHTTP") } catch(e) {}
    try { return new ActiveXObject("Microsoft.XMLHTTP") } catch(e) {}
    throw new Error( "This browser does not support XMLHttpRequest." )
  };
  return new XMLHttpRequest();
}

function handler(text) {
	if(AJAX.readyState == 4 && AJAX.status == 200) {
		var json = eval('(' + AJAX.responseText +')');
		document.getElementById("suggestions").innerHTML=json+'';
	}else if (AJAX.readyState == 4 && AJAX.status != 200) {
		document.getElementById("suggestions").innerHTML='Something went wrong...';
	}
}


function findSimilar(text){
	AJAX.onreadystatechange = handler(text);
	if (text.length%2==0){  
		AJAX.open("GET", "UserListAjax?operation=all&str="+text);
		AJAX.send("");
	}
};

function handlerExist() {
	if(AJAX2.readyState == 4 && AJAX2.status == 200) {
		var resposta = AJAX2.responseText;
		document.getElementById("suggestions").innerHTML=resposta+'';
	}else if (AJAX2.readyState == 4 && AJAX2.status != 200) {
		document.getElementById("suggestions").innerHTML='Something went wrong...';
	}
}

function exist(text){
	AJAX2.onreadystatechange = handlerExist;
	AJAX2.open("GET", "UserListAjax?operation=exist&str="+text);
	AJAX2.send("");
};
