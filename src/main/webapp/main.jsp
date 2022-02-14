<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>Request room</title>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
</head>
<body>
	<header>
		<a href="controller?command=main"> <img src="static/images/hotel_logo.png" id="hotel-logo"
			alt="hotel logo"></a>
		<div class="navigation-bar">
			<a href="">log in </a>|<a href=""> registration </a>|<a href=""> request room </a> |<a href="">
				requests</a>
		</div>
	</header>
	<form method="GET" action="controller?command=login">
		<select name="room-capacity">
			<option value="1" selected>1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
		</select>
		<input type="text" placeholder="name" class="text-field" id="login-field" name="name">
		<input type="text" placeholder="surname" class="text-field" id="login-field" name="surname">
		<label for="start">Start date:</label>
		<input type="date" id="start" name="trip-start" value="2018-07-22" min="2018-01-01"
			max="2018-12-31">
		<input type="text" placeholder="type of apartments" class="text-field" id="login-field"
			name="surname">
		<input type="submit" id="log-in-text" value="log in">
	</form>
</body>
</html>