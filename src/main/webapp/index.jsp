<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="WEB-INF/custom-tag.tld" prefix="custom"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="/messages/messages" />
<c:set var="currentPageCommand" value="/controller?command=showPage&page=index.jsp" scope="session" />
<c:set var="salary" scope="page" value="" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="main-page" /></title>
<base href="http://localhost:8080/HotelBooking/">
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/index-style.css" type="text/css" />
</head>
<body class="${isLoggedIn ? 'main-page-body' : 'login-page-body'}">
	<c:choose>
		<c:when test="${not empty userId}">
			<c:choose>
				<c:when test="${isAdmin}">
					<jsp:include page="/WEB-INF/view/utility-pages/adminHeader.jsp"></jsp:include>
				</c:when>
				<c:otherwise>
					<jsp:include page="/WEB-INF/view/utility-pages/userHeader.jsp"></jsp:include>
				</c:otherwise>
			</c:choose>
			<div id="main-page-content">
				<h1 id="main-heading">
					<fmt:message key="lookingForRoom" />
				</h1>
				<p>
					<fmt:message key="description" />
				</p>
				<div id="background-circle">
					<img src="static/images/hotel.png" alt="hotel photo">
				</div>
				<a href="controller?command=showPage&page=WEB-INF/view/user-pages/requestRoom.jsp"><input
						type="button" value=" <fmt:message key="request" />"></a>
			</div>
		</c:when>
		<c:otherwise>
			<div id="language-bar">
				<jsp:include page="/WEB-INF/view/utility-pages/languageBar.jsp"></jsp:include></div>
			<div class="log-in-box">
				<h1 id="welcome-back-msg">
					<custom:printWelcome lang="${sessionScope.lang}" />
				</h1>
				<form method="POST" action="controller?command=login">
					<input type="text" id="login-input" name="login"
						placeholder="<fmt:message
                            key="login" />" maxlength="10"
						pattern="[\d|\w]{1,10}" required>
					<input type="password" id="password-input" name="password" maxlength="10"
						pattern="[\d|\w]{1,10}"
						placeholder="<fmt:message
                            key="password" />" required>
					<br></br> <a href="controller?command=showPage&page=registrationPage.jsp" id="registration-msg">
						<fmt:message key="registrationProposal" />
					</a>
					<input type="submit" id="log-in-btn"
						value="<fmt:message
                            key="log-in" />">
					<div class="error-msg">
						<c:if test="${not empty errorMessage}">
							<fmt:message key="${errorMessage}" />
						</c:if>
					</div>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>