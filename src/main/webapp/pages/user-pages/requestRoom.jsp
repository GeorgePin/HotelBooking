<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Request room page</title>
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
	<h1 id="choose-a-room">
		<fmt:message key="choose-a-room" />
	</h1>
	<form method="POST" action="controller?command=requestRoom">
		<select name="roomCapacity">
			<option value="1" selected>1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
		</select>
		<input type="date" id="start-date" name="startDate" value="2022-03-07" min="2022-03-07"
			max="2023-03-07">
		<label for="start-date">
			<fmt:message key="start-date" />
		</label>
		<input type="date" id="end-date" name="endDate" value="2022-03-07" min="2022-03-07"
			max="2023-03-07">
		<label for="end-date">
			<fmt:message key="end-date" />
		</label>
		<select name="roomClass">
			<option value="standart" selected><fmt:message key="standart" /></option>
			<option value="deluxe"><fmt:message key="deluxe" /></option>
			<option value="premium"><fmt:message key="premium" /></option>
		</select>
		<input type="submit" id="apply" value="<fmt:message key="apply" />">
	</form>
</body>
</html>