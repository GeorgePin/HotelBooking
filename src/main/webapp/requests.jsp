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
		<c:forEach var="request" items="${requestsList}">
			<tr>
				<td>${request.id}</td>
			</tr>
		</c:forEach>
	</table>

	<%--For displaying Previous link except for the 1st page --%>
	<%--    <c:if test="${currentPage != 1}">
        <td><a href="employee.do?page=${currentPage - 1}">Previous</a></td>
    </c:if> --%>

	<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${numberOfPages}" var="i">
				<td><a href="controller?command=requestsPage&page=${i}">${i}</a></td>
			</c:forEach>
		</tr>
	</table>

	<%--For displaying Next link --%>
	<%--   <c:if test="${currentPage lt noOfPages}">
        <td><a href="employee.do?page=${currentPage + 1}">Next</a></td>
    </c:if> --%>
</body>

<%-- <body>
	<c:set var="CONTENT_PER_PAGE" value="5" />
	<sql:setDataSource var="database" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/hotel" user="root" password="root" />

	<sql:query dataSource="${database}" var="result">
        select * from reservation;
      </sql:query>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<table>
		<tr>
			<th>Request ID</th>
			<th>User ID</th>
			<th>Room Capacity</th>
			<th>Room Class</th>
			<th>Start Date</th>
			<th>End Date</th>
		</tr>
		<c:forEach var="row" items="${requests}" begin="5" end="6">
			<c:choose>
				<c:when test="${!row.is_approved}">
				<c:forEach var="request" items="${requests}">
			<tr>
						<td><c:out value="${request.id}" /></td>

				<td><a
					href="controller?command=RequestHandlingPage&requestId=
					${row.id}&userId=${row.user_id}&roomCapacity=${row.room_capacity}
					&roomClass=${row.room_class}&startDate=${row.start_date}&endDate=${row.end_date}"><c:out
							value="${row.id}" />dd</a></td>
				<c:out value="${NUM_STEPS}" />
					<td><c:out value="${row.user_id}" /></td>
						<td><c:out value="${row.room_capacity}" /></td>
						<td><c:out value="${row.room_class}" /></td>
						<td><c:out value="${row.start_date}" /></td>
						<td><c:out value="${row.end_date}" /></td> 
			</tr>
					</c:forEach>
			</c:when>
			</c:choose>
		</c:forEach>
	</table>
</body> --%>
</html>