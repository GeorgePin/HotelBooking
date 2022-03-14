<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<c:set var="currentPageCommand" value="/controller?command=createRoomPage" scope="session" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="create-room-page" /></title>
<base href="http://localhost:8080/HotelBooking/">
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/create-room-style.css" type="text/css" />
</head>
<body>
	<jsp:include page="/pages/utility-pages/adminHeader.jsp"></jsp:include>
	<main>
		<form method="POST" action="controller?command=createRoom">
			<div id="flex-container">
				<div id="left-column">
					<label>
						<fmt:message key="room-capacity" />
						<select name="roomCapacity">
							<option value="1" selected>1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</label>
					<label>
						<fmt:message key="room-number" />
						<input type="text" id="number-of-room" name="numberOfRoom" maxlength="3" pattern="\d{3}"
							title="Number of room must contain exactly 3 digits">
					</label>
				</div>
				<div id="right-column">
					<label>
						<fmt:message key="room-class" />
						<select name="roomClass">
							<option value="standart" selected>standart</option>
							<option value="deluxe">deluxe</option>
							<option value="premium">premium</option>
						</select>
					</label>
					<label>
						<fmt:message key="room-price" />
						<select name="idOfPrice" id="price-of-room">
							<c:forEach var="price" items="${listOfPrices}">
								<option value="${price.id}">${price.price}</option>
							</c:forEach>
						</select>
					</label>
				</div>
			</div>
			<input type="submit" id="create-room-btn" value="<fmt:message key="create-room-btn" />">
		</form>
	</main>
</body>
</html>