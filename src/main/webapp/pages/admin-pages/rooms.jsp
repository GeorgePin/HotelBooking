<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<c:set var="currentPageCommand" value="/controller?command=roomsPage&page=1" scope="session" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Rooms page</title>
<base href="http://localhost:8080/HotelBooking/">
<script src="static/js/scripts.js"></script>
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/rooms-style.css" type="text/css" />
</head>
<body>
	<jsp:include page="/pages/utility-pages/adminHeader.jsp"></jsp:include>
	<div id="page-content">
		<table id="rooms-table">
			<tr>
				<th id="counter-row">#</th>
				<th><p class="table-heading-text">
						<fmt:message key="room-number" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="room-capacity" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="room-class" />
					</p></th>
				<th id="is-blocked-row"><p class="table-heading-text">
						<fmt:message key="is-room-blocked" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="room-price" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="valid-from" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="unblock-room" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="delete-room" />
					</p></th>
			</tr>
			<c:forEach var="room" items="${roomsList}" varStatus="counter">
				<tr>
					<td><p class="table-text"><c:out value="${counter.count}" /></p></td>
					<td><p class="table-text">${room.number}</p></td>
					<td><p class="table-text">${room.capacity}</p></td>
					<td><p class="table-text"><fmt:message key="${room.roomClass}" /></p></td>
					<c:choose>
						<c:when test="${room.isBlocked}">
							<td><img src="static/images/accept.png" alt="accpect image" class="accept-image"></td>
						</c:when>
						<c:otherwise>
							<td><img src="static/images/pending.png" alt="pending image" class="pending-image"></td>
						</c:otherwise>
					</c:choose>
					<td><p class="table-text">${room.roomPrice.price}</p></td>
					<td><p class="table-text">${room.roomPrice.validFrom}</p></td>
					<td><a href="controller?command=unblockRoom&roomId=${room.id}"
						onclick="return ConfirmDelete()"> <fmt:message key="unblock-room" />
					</a></td>
					<td><a href="controller?command=deleteRoom&roomId=${room.id}"
						onclick="return ConfirmDelete()"> <fmt:message key="delete-room" />
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<table class="page-navigation">
		<tr>
			<c:forEach begin="1" end="${numberOfPages}" var="i">
				<td><a href="controller?command=roomsPage&page=${i}">${i}</a></td>
			</c:forEach>
		</tr>
	</table>
</body>