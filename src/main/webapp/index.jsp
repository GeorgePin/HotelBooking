<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
</head>
<body>
	<header>
		<a href="controller?command=main"> <img src="static/images/hotel_logo.png" id="hotel-logo"
			alt="hotel logo"></a>
		<div class="navigation-bar">
			<a href="">log in </a>|<a href=""> registration </a>|<a href="controller?command=requestRoomPage">
				request room </a> |<a href="controller?command=requestsPage"> requests</a>|<a href="controller?command=logout"> log out</a>
		</div>
	</header>
	<c:choose>
		<c:when test="${isLoggedIn}">
			<h1 id="heading">Looking for room?</h1>
			<p>It is a long established fact that a reader will be distracted by the readable content of
				a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less
				normal distribution of letters, as opposed to using 'Content here, content here', making it look
				like readable English.</p>
			<div id="background-circle" class="hotel-photo">
				<img src="static/images/hotel.png" alt="hotel photo">
			</div>
			<a href="controller?command=requestRoom"><input type="button" id="requset-button"
					value="request!"></a>
		</c:when>
		<c:otherwise>
			<h1 id="welcome">Welcome back!</h1>
			<form method="POST" action="controller?command=login">
				<input type="text" placeholder="login" class="text-field" id="login-field" name="login">
				<input type="password" placeholder="password" class="text-field" id="password-field"
					name="password">
				<input type="submit" id="log-in-text" value="log in">
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>