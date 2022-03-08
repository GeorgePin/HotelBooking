<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Requets page</title>
<base href="http://localhost:8080/HotelBooking/">
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/requests-style.css" type="text/css" />
</head>
<body>
	<div id="page-content">
		<table id="requets-table">
			<tr>
				<th>#</th>
				<th>start-date</th>
				<th>end-date</th>
				<th>room-capacity</th>
				<th>room-class</th>
				<th>is-approved</th>
				<th>room-price</th>
			</tr>
			<c:forEach var="request" items="${requestsList}" varStatus="counter">
				<tr>
					<td><a href="controller?command=requestHandlingPage&requestId=${request.id}"><c:out
								value="${counter.count}" /></a></td>
					<td>${request.startDate}</td>
					<td>${request.endDate}</td>
					<td>${request.roomCapacity}</td>
					<td>${request.roomClass}</td>
					<td>${request.isApproved}</td>
					<td>${request.price}</td>
				</tr>
			</c:forEach>
		</table>
		<table id="page-navigation">
			<tr>
				<c:forEach begin="1" end="${numberOfPages}" var="i">
					<td><h2>
							<a href="controller?command=requestsPage&page=${i}">${i}</a>
						</h2></td>
				</c:forEach>
			</tr>
		</table>
	</div>
</html>