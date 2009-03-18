<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="br.bookmark.db.Database"%>
<%@page import="br.bookmark.db.DataBaseUtils"%>
<%@page import="br.bookmark.project.*"%>
<%@page import="br.bookmark.models.*"%>
<% 
	Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
    UserMgr userMgr = new UserMgr(bookmarkInit.getUserDAO());
    if (userMgr == null){
    	response.sendRedirect("error.jsp");
    }
    
	//Le valores passados por p
	String login = request.getParameter("login");
	String password = request.getParameter("password");

	if ((login != null)&&(password!=null)) {
	
		// Verifica o login e senha
		User user = userMgr.validateUser(login, password);
		if (user == null) {
			response.sendRedirect("error.jsp");
		} else {
			// Login valido
			session.setAttribute("idUser", user.getId());
			response.sendRedirect("../bookmark/bookmarkList.jsp");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta content="text/html; charset=UTF-8" http-equiv="content-type" />
	<title>Login</title>
	<link rel="stylesheet" href="../css/style.css" type="text/css" />	
</head>
<body>
<div id="wrap">
	<div id="container">
	
	<div id="header">
	<div id="caption">
<div id="title">
<h1>
<a title="Home" href="/">Paulino Michelazzo</a>
</h1>
</div>
<div id="tagline"> Maçãs cercadas de pinguins </div>
<ul class="secondary-links">
<li class="menu-155 first">
<a title="Entre em contato comigo!" href="/contact">contato</a>
</li>
<li class="menu-224">
<a title="Sobre este website" href="/pagina/sobre-este-website">sobre</a>
</li>
<li class="menu-159 last">
<a title="Acesse via RSS" href="/rss.xml">rss</a>
</li>
</ul>
</div>
<div id="navigation">
<div id="menus">
<ul class="links">
<li class="menu-114 active-trail first active">
<a class="active" title="Início" href="/">Home</a>
</li>
<li class="menu-192">
<a title="" href="/book">Tutoriais</a>
</li>
<li class="menu-193 last">
<a title="Serviços" href="/pagina/servicos">Serviços</a>
</li>
</ul>
</div>
<div id="searchbox">
<form id="search-theme-form" method="post" accept-charset="UTF-8" action="/">
<div>
<div id="search" class="container-inline">
<div id="edit-search-theme-form-1-wrapper" class="form-item">
<label for="edit-search-theme-form-1"> </label>
<input id="edit-search-theme-form-1" class="form-text" type="text" title="Enter the terms you wish to search for." value="" size="15" name="search_theme_form" maxlength="128"/>
</div>
<input id="edit-submit-1" class="form-submit" type="submit" value="Pesquisar" name="op"/>
<input id="form-80acbdf205938e5416a7db933bb5af33" type="hidden" value="form-80acbdf205938e5416a7db933bb5af33" name="form_build_id"/>
<input id="edit-search-theme-form" type="hidden" value="search_theme_form" name="form_id"/>
</div>
</div>
</form>
</div>
</div>
</div>
	
		<div id="content">
			Login
			<br />
			<a href="register.jsp">sign up</a>
			<br />
			<form action="login.jsp" method="post" name="formLogin">
			Username:<br />
			<input name="login" /> <br />
			<br />
			Password: <br />
			<input name="password" type="password" /> <br />
			<br />
			<a href="forgot.jsp">forgot password</a> <br />
			<input name="send" value="Send" type="submit" /> <br />
			<br />
			</form>
		
		</div>
	</div>
</div>

</body>
</html>