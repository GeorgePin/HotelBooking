<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="/messages/messages" />
<c:set var="currentPageCommand"
	value="/controller?command=showPage&page=WEB-INF/view/user-pages/requestRoom.jsp" scope="session" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="request-room-page" /></title>
<script src="static/js/scripts.js"></script>
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/request-room-style.css" type="text/css" />
</head>
<jsp:include page="/WEB-INF/view/utility-pages/userHeader.jsp"></jsp:include>
<body>
	<form method="POST" action="controller?command=requestRoom" id="request-room-content">
		<h1 id="choose-a-room">
			<fmt:message key="choose-a-room" />
		</h1>
		<div class="input-row">
			<div id="start-date" class="label-and-input-pair">
				<label for="start-date">
					<fmt:message key="start-date" />
				</label>
				<input type="date" name="startDate" id="startDate" onchange=updateEndDate() required>
			</div>
			<div id="end-date" class="label-and-input-pair">
				<label for="end-date">
					<fmt:message key="end-date" />
				</label>
				<input type="date" name="endDate" id="endDate">
			</div>
		</div>
		<div class="input-row">
			<div id="room-capacity" class="label-and-input-pair">
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
			<div id="room-class" class="label-and-input-pair">
				<label for="room-class">
					<fmt:message key="room-class" />
				</label>
				<select name="roomClass">
					<option value="standart" selected><fmt:message key="standart" /></option>
					<option value="deluxe"><fmt:message key="deluxe" /></option>
					<option value="premium"><fmt:message key="premium" /></option>
				</select>
				<input type="hidden" name="userId" value="${sessionScope.userId}">
			</div>
		</div>
		<input type="submit" id="apply" value="<fmt:message key="apply" />">
	</form>
	<script>
		initialDatesBinding();
	</script>
</body>
</html>