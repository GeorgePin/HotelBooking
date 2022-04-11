<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="/messages/messages" />
<c:set var="currentPageCommand" value="/controller?command=roomsPage&page=1" scope="session" />
<c:set var="DatePattern">
	<fmt:message key="date" />
</c:set>
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="rooms-page" /></title>
<base href="http://localhost:8080/HotelBooking/">
<script src="static/js/scripts.js"></script>
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/rooms-style.css" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/view/utility-pages/adminHeader.jsp"></jsp:include>
	<div id="page-content">
		<table id="rooms-table">
			<thead>
				<tr>
					<th id="counter-row" class="number-column"><p class="table-heading-text">#</p></th>
					<th class="room-number-column"><p class="table-heading-text">
							<fmt:message key="room-number" />
						</p></th>
					<th class="room-capacity-column"><p class="table-heading-text">
							<fmt:message key="room-capacity" />
						</p></th>
					<th class="room-class-column"><p class="table-heading-text">
							<fmt:message key="room-class" />
						</p></th>
					<th class="room-price-column"><p class="table-heading-text">
							<fmt:message key="room-price" />
						</p></th>
					<th class="room-price-date-column"><p class="table-heading-text">
							<fmt:message key="valid-from" />
						</p></th>
					<th class="is-blocked-column"><p class="table-heading-text">
							<fmt:message key="is-room-blocked" />
						</p></th>
					<th class="set-room-state-column"><p class="table-heading-text">
							<fmt:message key="block-unblock" />
						</p></th>
					<th class="edit-room-column"><p class="table-heading-text">
							<fmt:message key="edit-room" />
						</p></th>
					<th class="delete-room-column"><p class="table-heading-text">
							<fmt:message key="delete-room" />
						</p></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="room" items="${roomsList}" varStatus="counter">
					<tr>
						<td id="room-number-data"><p class="table-text">
								<c:out value="${counter.count}" />
							</p></td>
						<td><p class="table-text">${room.number}</p></td>
						<td id="room-capacity-data"><p class="table-text">${room.capacity}</p></td>
						<td><p class="table-text">
								<fmt:message key="${room.roomClass}" />
							</p></td>
						<td><p class="table-text">${room.roomPrice.price}</p></td>
						<td id="price-date-data"><p class="table-text">
								<fmt:formatDate pattern="${DatePattern}" value="${room.roomPrice.validFrom}" />
							</p></td>
						<c:choose>
							<c:when test="${room.isBlocked}">
								<td><img src="static/images/accept.png" alt="accpect image" class="accept-image"></td>
								<td><a class="in-column-link"
									href="controller?command=setRoomState&roomId=${room.id}&state=0"><fmt:message
											key="unban" /></a></td>
							</c:when>
							<c:otherwise>
								<td><img src="static/images/pending.png" alt="pending room" class="pending-image"></td>
								<td><a class="in-column-link"
									href="controller?command=setRoomState&roomId=${room.id}&state=1"
									onclick="return ConfirmDelete()"><fmt:message key="ban" /></a></td>
							</c:otherwise>
						</c:choose>
						<td><a class="in-column-link" href="controller?command=editRoomPage&roomId=${room.id}">
								<fmt:message key="edit-room" />
						</a></td>
						<td><a class="in-column-link" href="controller?command=deleteRoom&roomId=${room.id}"
							onclick="return ConfirmDelete()"> <fmt:message key="delete-room" />
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table class="page-navigation">
			<tr>
				<c:forEach begin="1" end="${numberOfPages}" var="i">
					<td><a href="controller?command=roomsPage&page=${i}">${i}</a></td>
				</c:forEach>
			</tr>
		</table>
		<button id="create-room-btn" onclick="location.href='controller?command=createRoomPage'"
			type="button">
			<fmt:message key="create-room-btn" />
		</button>
	</div>
</body>