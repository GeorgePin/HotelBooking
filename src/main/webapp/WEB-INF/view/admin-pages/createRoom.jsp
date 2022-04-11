<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="/messages/messages" />
<c:set var="currentPageCommand" value="/controller?command=createRoomPage&page=${param.page}"
	scope="session" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="create-room-page" /></title>
<base href="http://localhost:8080/HotelBooking/">
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/create-room-style.css" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/view/utility-pages/adminHeader.jsp"></jsp:include>
	<main>
		<form method="POST" action="controller?command=createRoom" id="create-room-content">
			<div class="input-row">
				<div id="capacity" class=label-and-input-pair>
					<label for="capacity">
						<fmt:message key="room-capacity" />
					</label>
					<select name="capacity">
						<option value="1" selected>1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select>
				</div>
				<div id="room-number" class=label-and-input-pair>
					<label for="room-number">
						<fmt:message key="room-number" />
					</label>
					<input type="text" id="number-of-room" name="number" maxlength="3" pattern="^(?!0)\d{1,3}"
						required>
				</div>
			</div>
			<div class="input-row">
				<div id="room-class" class=label-and-input-pair>
					<label for="room-class">
						<fmt:message key="room-class" />
					</label>
					<select name="roomClass">
						<option value="standart" selected>
							<fmt:message key="standart" />
						</option>
						<option value="deluxe">
							<fmt:message key="deluxe" /></option>
						<option value="premium">
							<fmt:message key="premium" /></option>
					</select>
				</div>
				<div id="room-price" class=label-and-input-pair>
					<label for="room-price">
						<fmt:message key="room-price" />
					</label>
					<select name="roomPriceId" id="price-of-room">
						<c:forEach var="price" items="${listOfPrices}">
							<option value="${price.id}">${price.price}</option>
						</c:forEach>
					</select>

				</div>
			</div>
			<input type="submit" id="create-room-btn" value="<fmt:message key="create-room-btn" />">
			<div class="error-msg">
				<c:if test="${not empty errorMessage}">
					<fmt:message key="${errorMessage}" />
				</c:if>
			</div>
		</form>
	</main>
</body>
</html>