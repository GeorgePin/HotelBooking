<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
<title>Request handling page</title>
</head>
<body>
	<sql:setDataSource var="database" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/hotel" user="root" password="root" />

	<sql:query dataSource="${database}" var="result">
        select * from room where is_blocked='0';
      </sql:query>

	<table>
		<tr>
			<th>ID</th>
			<th>Capacity</th>
			<th>type</th>
			<th>number</th>
			<th>room price</th>
		</tr>
		<c:forEach var="row" items="${result.rows}">
			<tr>
				<td><a
					href="controller?command=RequestHandling&requestId=${param.requestId}&roomId=${row.id}"><c:out
							value="${row.id}" /></a></td>
				<td><c:out value="${row.capacity}" /></td>
				<td><c:out value="${row.type}" /></td>
				<td><c:out value="${row.number}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>