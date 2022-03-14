<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<c:set var="currentPageCommand"
	value="/controller?command=showPage&page=pages/user-pages/requestRoom" scope="session" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="request-room-page" /></title>
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/request-room-style.css" type="text/css" />
</head>
<c:choose>
	<c:when test="${isAdmin}">
		<jsp:include page="/pages/utility-pages/adminHeader.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="/pages/utility-pages/userHeader.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
<body>
	<div id="request-room-content">
		<h1 id="choose-a-room">
			<fmt:message key="choose-a-room" />
		</h1>
		<form method="POST" action="controller?command=requestRoom">
			<div id="room-capacity">
				<label for="room-capacity">
					<fmt:message key="room-capacity" />
				</label>
				<select name="roomCapacity">
					<option value="1" selected>1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</div>
			<div id="start-date">
				<label for="start-date">
					<fmt:message key="start-date" />
				</label>
				<input type="date" name="startDate" value="2022-03-07" min="2022-03-07" max="2023-03-07">
			</div>
			<div id="end-date">
				<label for="end-date">
					<fmt:message key="end-date" />
				</label>
				<input type="date" name="endDate" value="2022-03-07" min="2022-03-07" max="2023-03-07">
			</div>
			<div id="room-class">
				<label for="room-class">
					<fmt:message key="room-class" />
				</label>
				<select name="roomClass">
					<option value="standart" selected><fmt:message key="standart" /></option>
					<option value="deluxe"><fmt:message key="deluxe" /></option>
					<option value="premium"><fmt:message key="premium" /></option>
				</select>
			</div>
			<input type="submit" id="apply" value="<fmt:message key="apply" />">
		</form>
	</div>
</body>
</html>