<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Admin header</title>
<link rel="stylesheet" href="static/styles/header-style.css" type="text/css" />
</head>
<header>
	<a href="controller?command=showPage&page=index" id="hotel-logo"> <img
		src="static/images/hotel_logo.png" alt="hotel logo"></a>
	<div class="navigation-bar">
		<a href="controller?command=roomsPage&page=1">rooms </a>|<a href="controller?command=clientsPage">
			clients </a>|<a href="controller?command=requestsPage&page=1"> requests </a>|<a
			href="controller?command=logout"> log out</a>
	</div>
	<div id="language-bar">
		<jsp:include page="/pages/utility-pages/languageBar.jsp"></jsp:include></div>
</header>
</html>