<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="static/styles/rooms-style.css" type="text/css" />
<script type="text/javascript" src="static/js/scripts.js"></script>
<title>Rooms page</title>
</head>
<body>
    <jsp:include page="/pages/utility-pages/adminHeader.jsp"></jsp:include>
	<table id="rooms-table" border="1" cellpadding="5" cellspacing="5">
		<tr>
			<th>Room number</th>
			<th>Capacity</th>
			<th>Type</th>
			<th>Is blocked</th>
			<th>Price</th>
		</tr>
		<c:forEach var="room" items="${roomsList}">
			<tr>
				<td>${room.number}</td>
				<td>${room.capacity}</td>
				<td>${room.type}</td>
				<td>${room.isBlocked}</td>
				<td>${room.roomPrice}</td>
				<td><a href="controller?command=deleteRoom&roomId=${room.id}"
					onclick="return ConfirmDelete()">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<table id="page-navigation-table" border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${numberOfPages}" var="i">
				<td><a href="controller?command=roomsPage&page=${i}">${i}</a></td>
			</c:forEach>
		</tr>
	</table>
	<a id="create-new-room" href="controller?command=createRoomPage">create new room</a>
</body>