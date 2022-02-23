<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta charset="UTF-8">
<title>Main page</title>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
<!-- <script type="text/javascript" src="static/js/scripts.js"></script>
    <a href="controller?command=main" onclick="return ConfirmDelete()">ddd</a> -->
</head>
<body>
	<sql:setDataSource var="database" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/hotel" user="root" password="root" />
	<c:choose>
		<c:when test="${isLoggedIn}">
			<sql:query dataSource="${database}" var="result">
       select * from user where id=${user};
    </sql:query>
			<c:forEach var="row" items="${result.rows}">
				<c:choose>
					<c:when test="${row.is_admin}">
						<jsp:include page="adminHeader.jsp" />
					</c:when>
					<c:otherwise>
						<jsp:include page="userHeader.jsp" />
						<h1 id="heading">Looking for room?</h1>
						<p>It is a long established fact that a reader will be distracted by the readable content
							of a page when looking at its layout. The point of using Lorem Ipsum is that it has a
							more-or-less normal distribution of letters, as opposed to using 'Content here, content
							here', making it look like readable English.</p>
						<div id="background-circle" class="hotel-photo">
							<img src="static/images/hotel.png" alt="hotel photo">
						</div>
						<a href="controller?command=requestRoomPage"><input type="button" id="requset-button"
								value="request!"></a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h1 id="welcome">Welcome back!</h1>
			<form method="POST" action="controller?command=login">
				<input type="text" placeholder="login" class="text-field" id="login-field" name="login">
				<input type="password" placeholder="password" class="text-field" id="password-field"
					name="password">
				<a href="controller?command=registrationPage">don't have account? Register now!</a>
				<input type="submit" id="log-in-text" value="log in">
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>