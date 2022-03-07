<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
<title>Requests page</title>
</head>
<body>
<body>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<th>ID</th>
		</tr>
		<c:forEach var="request" items="${requestsList}" varStatus="counter">
			<tr>
				<td><a href="controller?command=requestHandlingPage&requestId=${request.id}"><c:out
							value="${counter.count}" /></a></td>
				<td>${request.startDate}</td>
				<td>${request.endDate}</td>
				<td>${request.roomCapacity}</td>
				<td>${request.roomClass}</td>
			</tr>
		</c:forEach>
	</table>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${numberOfPages}" var="i">
				<td><a href="controller?command=requestsPage&page=${i}">${i}</a></td>
			</c:forEach>
		</tr>
	</table>
</html>