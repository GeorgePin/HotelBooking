<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<c:set var="currentPageCommand"
	value="/controller?command=requestHandlingPage&requestId=${param.requestId}&page=${param.page}"
	scope="session" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Request handling page</title>
<base href="http://localhost:8080/HotelBooking/">
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/request-handling-style.css" type="text/css" />
</head>
<body>
	<jsp:include page="/pages/utility-pages/adminHeader.jsp"></jsp:include>
	<div id="page-content">
		<table id="request-table">
			<tr>
				<th><p class="table-heading-text">
						<fmt:message key="user-id" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="start-date" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="end-date" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="room-capacity" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="room-class" />
					</p></th>
			</tr>
			<tr>
				<td><p class="table-text">${requestScope.request.userId}</p></td>
				<td><p class="table-text">${requestScope.request.startDate}</p></td>
				<td><p class="table-text">${requestScope.request.endDate}</p></td>
				<td><p class="table-text">${requestScope.request.roomCapacity}</p></td>
				<td><p class="table-text">
						<fmt:message key="${requestScope.request.roomClass}" />
					</p></td>
			</tr>
		</table>
		<table id="rooms-table">
			<tr>
				<th><p class="table-heading-text">#</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="room-capacity" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="room-class" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="number" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="room-price" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="valid-from" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="take-it" />
					</p></th>
			</tr>
			<c:forEach var="room" items="${listOfRooms}" varStatus="counter">
				<tr>
					<td><p class="table-text">${counter.count}</p></td>
					<td><p class="table-text">${room.capacity}</p></td>
					<td><p class="table-text">
							<fmt:message key="${room.roomClass}" />
						</p></td>
					<td><p class="table-text">${room.number}</p></td>
					<td><p class="table-text">${room.roomPrice.price}</p></td>
					<td><p class="table-text">${room.roomPrice.validFrom}</p></td>
					<td><a
						href="controller?command=requestHandling&requestId=${param.requestId}&roomId=${room.id}"><fmt:message
								key="take-it" /></a></td>
				</tr>
			</c:forEach>
		</table>
		<table class="page-navigation">
			<tr>
				<c:forEach begin="1" end="${numberOfPages}" var="i">
					<td><h2>
							<a
								href="controller?command=requestHandlingPage&requestId=${requestScope.request.id}&page=${i}">${i}</a>
						</h2></td>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>