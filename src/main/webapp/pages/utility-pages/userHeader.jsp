<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>User header</title>
<link rel="stylesheet" href="static/styles/header-style.css" type="text/css" />
</head>
<header>
	<a href="controller?command=showPage&page=pages/common-pages/index"> <img
		src="static/images/hotel_logo.png" alt="hotel logo" id="hotel-logo"></a>
	<div class="navigation-bar">
		<a href="controller?command=showPage&page=pages/user-pages/requestRoom"> <fmt:message key="request-room" />
		</a> | <a href="controller?command=showPage&page=pages/common-pages/requests"><fmt:message key="requests" /></a> | <a
			href="controller?command=logout"><fmt:message key="log-out" /></a>
	</div>
	<div id="language-bar">
		<jsp:include page="/pages/utility-pages/languageBar.jsp"></jsp:include></div>
</header>
</html>