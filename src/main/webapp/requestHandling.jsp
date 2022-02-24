<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
<title>Request handling page</title>
</head>
<body>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<th>Order Number</th>
			<th>Capacity</th>
			<th>type</th>
			<th>number</th>
			<th>room price</th>
			<th>take it</th>
		</tr>
		<c:forEach var="room" items="${listOfRooms}" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td>${room.capacity}</td>
				<td>${room.type}</td>
				<td>${room.number}</td>
				<td><c:forEach var="roomPrice" items="${listOfPrices}">
						<c:if test="${roomPrice.id==room.roomPriceId}">
						${roomPrice.price}
						</c:if>
					</c:forEach></td>
				<td><a
					href="controller?command=requestHandling&requestId=${param.requestId}&roomId=${room.id}">take
						it</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>