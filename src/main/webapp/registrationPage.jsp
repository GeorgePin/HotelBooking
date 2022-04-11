<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="/messages/messages" />
<c:set var="currentPageCommand" value="/controller?command=showPage&page=registrationPage.jsp"
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
	<jsp:include page="/WEB-INF/view/utility-pages/languageBar.jsp"></jsp:include>
	<main>
		<form method="POST" action="controller?command=register" id="registration-forms">
			<h1 id="lets-be-friends-msg">
				<fmt:message key="beFriends" />
			</h1>
			<div class="pair-of-fields">
				<input type="text" id="name-input" name="name" minlength="3" maxlength="20"
					pattern="[a-zA-Z]{3,20}" placeholder="<fmt:message
                            key="name" />"
					required>
				<input type="text" id="surname-input" name="surname" minlength="3" maxlength="20"
					pattern="[a-zA-Z]{3,20}"
					placeholder="<fmt:message
                            key="surname" />" required>
			</div>
			<div class="pair-of-fields">
				<input type="text" id="login-input" name="login" minlength="3" maxlength="10"
					pattern="[\d|\w]{3,10}" placeholder="<fmt:message
                            key="login" />"
					required>
				<input type="password" id="password-input" name="password" minlength="3" maxlength="10"
					pattern="[\d|\w]{3,10}"
					placeholder="<fmt:message
                            key="password" />" required>
			</div>
			<input type="submit" id="register-btn"
				value="<fmt:message
                            key="register-btn" />">
			<div class="error-msg">
				<c:if test="${not empty errorMessage}">
					<fmt:message key="${errorMessage}" />
				</c:if>
			</div>
		</form>
	</main>
</body>
</html>