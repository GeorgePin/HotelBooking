<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="/messages/messages" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="error-page" /></title>
<base href="http://localhost:8080/HotelBooking/">
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/error-page-style.css" type="text/css" />
</head>
<body>
	<img id="sad-face-img" src="static/images/sad-face.png" alt="sad face photo">
	<h1 id="heading">
		<fmt:message key="smth-went-wrong" />
	</h1>
	<p id="error-msg-text">
		<fmt:message key="${errorMessage}" />
	</p>
	<button id="go-back-btn" onclick="location.href='index.jsp'" type="button">
		<fmt:message key="go-back-btn" />
	</button>
</body>
</html>