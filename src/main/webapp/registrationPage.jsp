<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="/messages/messages" />
<c:set var="currentPageCommand" value="/controller?command=showPage&page=registrationPage"
	scope="session" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="registration-page" /></title>
<base href="http://localhost:8080/HotelBooking/">
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/registration-style.css" type="text/css" />
</head>
<body>
	<div id="language-bar">
		<jsp:include page="/WEB-INF/view/utility-pages/languageBar.jsp"></jsp:include></div>
	<main>
		<h1 id="lets-be-friends-msg">
			<fmt:message key="beFriends" />
		</h1>
		<form method="POST" action="controller?command=register" id="registration-forms">
			<div>
				<input type="text" id="name-input" name="name" maxlength="10" pattern="[a-zA-z]{3,20}"
					placeholder="<fmt:message
                            key="name" />">
				<input type="text" id="surname-input" name="surname" maxlength="10" pattern="[a-zA-Z]{3,20}"
					placeholder="<fmt:message
                            key="surname" />">
				<br></br>
				<input type="text" id="login-input" name="login" maxlength="10" pattern="[\d|\w]{1,10}"
					placeholder="<fmt:message
                            key="login" />">
				<input type="password" id="password-input" name="password" maxlength="10"
					pattern="[\d|\w]{1,10}"
					placeholder="<fmt:message
                            key="password" />">
			</div>
			<input type="submit" id="register-btn"
				value="<fmt:message
                            key="register-btn" />">
		</form>
		<div class="error-msg">
			<c:if test="${not empty errorMessage}">
				<fmt:message key="${errorMessage}" />
			</c:if>
		</div>
	</main>
</body>
</html>