<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
<title>Requests page</title>
</head>
<body>
	<sql:setDataSource var="database" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/hotel" user="root" password="root" />

	<sql:query dataSource="${database}" var="result">
        select * from reservation;
      </sql:query>

	<table>
		<tr>
			<th>Request ID</th>
			<th>User ID</th>
			<th>Room Capacity</th>
			<th>Room Class</th>
			<th>Start Date</th>
			<th>End Date</th>
		</tr>
		<c:forEach var="row" items="${result.rows}">
			<tr>
				<td><a
					href="controller?command=RequestHandlingPage&requestId=
					${row.id}&userId=${row.user_id}&roomCapacity=${row.room_capacity}
					&roomClass=${row.room_class}&startDate=${row.start_date}&endDate=${row.end_date}"><c:out
							value="${row.id}" /></a></td>
				<td><c:out value="${row.user_id}" /></td>
				<td><c:out value="${row.room_capacity}" /></td>
				<td><c:out value="${row.room_class}" /></td>
				<td><c:out value="${row.start_date}" /></td>
				<td><c:out value="${row.end_date}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>