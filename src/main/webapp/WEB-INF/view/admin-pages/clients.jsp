<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<c:set var="currentPageCommand" value="/controller?command=clientsPage&page=1" scope="session" />
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="clients-page" /></title>
<base href="http://localhost:8080/HotelBooking/">
<script src="static/js/scripts.js"></script>
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/clients-style.css" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/view/utility-pages/adminHeader.jsp"></jsp:include>
	<div id="page-content">
		<table>
			<tr>
				<th><p class="table-heading-text">#</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="name" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="surname" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="login" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="is-user-blocked" />
					</p></th>
				<th><p class="table-heading-text">
						<fmt:message key="block-unblock" />
					</p></th>
			</tr>
			<c:forEach var="client" items="${listOfClients}" varStatus="counter">
				<tr>
					<td><p class="table-text">
							<c:out value="${counter.count}" />
						</p></td>
					<td><p class="table-text">
							<c:out value="${client.name}" />
						</p></td>
					<td><p class="table-text">
							<c:out value="${client.surname}" />
						</p></td>
					<td><p class="table-text">
							<c:out value="${client.login}" />
						</p></td>
					<c:choose>
						<c:when test="${client.isBlocked}">
							<td><img src="static/images/accept.png" alt="accpect image" class="accept-image"></td>
							<td><a href="controller?command=setUserState&userId=${client.id}&state=0"><fmt:message
										key="unban" /></a></td>
						</c:when>
						<c:otherwise>
							<td><img src="static/images/pending.png" alt="pending image" class="pending-image"></td>
							<td><a href="controller?command=setUserState&userId=${client.id}&state=1"
								onclick="return ConfirmDelete()"><fmt:message key="ban" /></a></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>
	<table class="page-navigation">
		<tr>
			<c:forEach begin="1" end="${numberOfPages}" var="i">
				<td><h2>
						<a href="controller?command=clientsPage&page=${i}">${i}</a>
					</h2></td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>