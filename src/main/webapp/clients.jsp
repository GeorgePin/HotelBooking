<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
<title>Clients page</title>
</head>
<body>
	<table>
		<tr>
			<th>#</th>
			<th>name</th>
			<th>surname</th>
			<th>login</th>
			<th>is blocked</th>
			<td></td>
		</tr>
		<c:forEach var="client" items="${listOfClients}">
			<tr>
				<td></td>
				<td><c:out value="${client.name}" /></td>
				<td><c:out value="${client.surname}" /></td>
				<td><c:out value="${client.login}" /></td>
				<td><c:out value="${client.isBlocked}" /></td>
				<td><a href="controller?command=banUser&userId=${client.id}">Ban</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>