<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="static/styles/admin-header-style.css" type="text/css" />
<meta charset="ISO-8859-1">
</head>
	<header>
		<a href="controller?command=main"> <img src="static/images/hotel_logo.png" id="hotel-logo"
			alt="hotel logo"></a>
		<div class="navigation-bar">
			<a href="controller?command=roomsPage&page=1">rooms </a>|<a href="controller?command=clientsPage">
				clients </a>|<a href="controller?command=requestsPage&page=1"> requests </a>|<a
				href="controller?command=logout"> log out</a>
		</div>
	</header>
</html>