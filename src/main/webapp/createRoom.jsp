<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Create room</title>
<link rel="stylesheet" href="static/styles/create-room-style.css" type="text/css" />
</head>
<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<form method="POST" action="controller?command=createRoom">
		<div id="input-data">
			<select name="roomCapacity">
				<option value="1" selected>1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
			</select>
			<input type="radio" id="is-blocked-true" name="isBlocked" value="true" checked>
			<label for="is-blocked-true">room is free</label>
			<input type="radio" id="is-blocked-false" name="isBlocked" value="false">
			<label for="is-blocked-false">room is not free</label>
			<br> <br>
			<input type="number" id="number-of-room" name="numberOfRoom" pattern="\d{3}"
				title="Number of room must contain exactly 3 digits">
			<select name="roomClass">
				<option value="standart" selected>standart</option>
				<option value="deluxe">deluxe</option>
				<option value="premium">premium</option>
			</select>
			<label for="price-of-room">Room price</label>
			<select name="idOfPrice" id="price-of-room">
				<c:forEach var="price" items="${listOfPrices}">
					<option value="${price.id}">${price.price}</option>
				</c:forEach>
			</select>
			<input type="submit" id="create-room-btn" value="create room">
		</div>
	</form>
</body>
</html>