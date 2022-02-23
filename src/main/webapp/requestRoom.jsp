<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>Request room</title>
<link rel="stylesheet" href="static/styles/style.css" type="text/css" />
</head>
<body>
	<jsp:include page="userHeader.jsp"></jsp:include>
	<form method="POST" action="controller?command=requestRoom">
		<div id="input-data">
			<select name="roomCapacity">
				<option value="1" selected>1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
			</select>
			<label for="start">Start date:</label>
			<input type="date" id="start" name="startDate" value="2018-07-22" min="2018-01-01"
				max="2018-12-31">
			<input type="date" id="start" name="endDate" value="2018-07-22" min="2018-01-01" max="2018-12-31">
			<select name="roomClass">
				<option value="standart" selected>standart</option>
				<option value="deluxe">deluxe</option>
				<option value="premium">premium</option>
			</select>
			<input type="submit" id="apply" value="apply">
		</div>
	</form>
</body>
</html>