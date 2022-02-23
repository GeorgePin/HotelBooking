<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>Registration page</title>
<link rel="stylesheet" href="static/styles/registration.css" type="text/css" />
</head>
<body>
	<jsp:include page="adminHeader.jsp"></jsp:include>
	<form method="POST" action="controller?command=registration">
		<div id="input-data">
			<input type="text" id="user-name" name="userName">
			<label for="user-name">Name</label>

			<input type="text" id="user-surname" name="userSurname">
			<label for="user-surname">Surname</label>

			<input type="text" id="user-login" name="userLogin">
			<label for="user-login">Login</label>

			<input type="text" id="user-password" name="userPassword">
			<label for="user-password">Password</label>
			<input type="submit" id="register-btn" value="register">
		</div>
	</form>
</body>
</html>