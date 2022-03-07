<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>language bar</title>
<link rel="stylesheet" href="static/styles/language-bar-style.css" type="text/css" />
</head>
<body>
	<div class="dropdown">
		<img class="dropbtn" alt="globus icon" src="static/images/globus.png" width="50px">
		<div class="dropdown-content">
			<a href="${pageContext.request.contextPath}/?sessionLocale=ru"><fmt:message key="ru" /> </a> <a href="?sessionLocale=en"><fmt:message
					key="en" /></a> <a href="?sessionLocale=fr"><fmt:message key="fr" /></a>
		</div>
	</div>
</body>
</html>