<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html lang="${sessionScope.lang}">
<head>
<meta charset="UTF-8">
<title>Main page</title>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
<!-- <script type="text/javascript" src="static/js/scripts.js"></script>
    <a href="controller?command=main" onclick="return ConfirmDelete()">ddd</a> -->
</head>
<body>
	<div class="dropdown">
		<img class="dropbtn" alt="globus icon" src="static/images/globus.png" width="50px">
		<div class="dropdown-content">
			<a href="${pageContext.request.contextPath}?sessionLocale=ru"><fmt:message key="ru" /> </a> <a
				href="${pageContext.request.contextPath}?sessionLocale=en"><fmt:message key="en" /></a> <a
				href="${pageContext.request.contextPath}?sessionLocale=fr"><fmt:message key="fr" /></a>
		</div>
	</div>
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
						<p>
							<fmt:message key="description" />
						</p>
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
				<a href="controller?command=registrationPage"> <fmt:message key="registrationProposal" />
				</a>
				<input type="submit" id="log-in-text" value="log in">
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>