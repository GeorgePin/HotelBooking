<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="/messages/messages" />
<c:set var="currentPageCommand" value="/controller?command=requestsPage&page=${param.page}"
	scope="session" />
<c:set var="DatePattern">
	<fmt:message key="date" />
</c:set>
<html lang="${sessionScope.lang}">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title><fmt:message key="requests-page" /></title>
<base href="http://localhost:8080/HotelBooking/">
<link rel="stylesheet" href="static/styles/basic-style.css" type="text/css" />
<link rel="stylesheet" href="static/styles/requests-style.css" type="text/css" />
</head>
<body>
	<c:choose>
		<c:when test="${isAdmin}">
			<jsp:include page="/WEB-INF/view/utility-pages/adminHeader.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/view/utility-pages/userHeader.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${isAdmin}">
			<div id="page-content">
				<table id="requests-table">
					<tr>
						<th><p class="table-heading-text">#</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="start-date" />
							</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="end-date" />
							</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="room-capacity" />
							</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="room-class" />
							</p></th>
					</tr>
					<c:forEach var="request" items="${requestsList}" varStatus="counter">
						<tr>
							<td><a href="controller?command=requestHandlingPage&requestId=${request.id}&page=1"><c:out
										value="${counter.count}" /></a></td>
							<td><p class="table-text">
									<fmt:formatDate pattern="${DatePattern}" value="${request.startDate}" />
								</p></td>
							<c:choose>
								<c:when test="${not empty request.endDate}">
									<td><p class="table-text">
											<fmt:formatDate pattern="${DatePattern}" value="${request.endDate}" />
										</p></td>
								</c:when>
								<c:otherwise>
									<td id="dash"><h1>-</h1></td>
								</c:otherwise>
							</c:choose>
							<td><p class="table-text">${request.roomCapacity}</p></td>
							<td><p class="table-text">
									<fmt:message key="${request.roomClass}" />
								</p></td>
						</tr>
					</c:forEach>
				</table>
				<table class="page-navigation">
					<tr>
						<c:forEach begin="1" end="${numberOfPages}" var="i">
							<td><h2>
									<a href="controller?command=requestsPage&page=${i}">${i}</a>
								</h2></td>
						</c:forEach>
					</tr>
				</table>
			</div>
		</c:when>
		<c:otherwise>
			<div id="page-content">
				<table id="requets-table">
					<tr>
						<th><p class="table-heading-text">#</th>
						<th><p class="table-heading-text">
								<fmt:message key="start-date" />
							</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="end-date" />
							</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="room-capacity" />
							</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="room-class" />
							</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="is-approved" />
							</p></th>
						<th><p class="table-heading-text">
								<fmt:message key="room-price" />
							</p></th>
					</tr>
					<c:forEach var="request" items="${requestsList}" varStatus="counter">
						<tr>
							<td><c:out value="${counter.count}" /></td>
							<td><p class="table-text">
									<fmt:formatDate pattern="${DatePattern}" value="${request.startDate}" />
								</p></td>
							<c:choose>
								<c:when test="${not empty request.endDate}">
									<td><p class="table-text">
											<fmt:formatDate pattern="${DatePattern}" value="${request.endDate}" />
										</p></td>
								</c:when>
								<c:otherwise>
									<td id="dash"><h1>-</h1></td>
								</c:otherwise>
							</c:choose>
							<td><p class="table-text">${request.roomCapacity}</p></td>
							<td><p class="table-text">
									<fmt:message key="${request.roomClass}" />
								</p></td>
							<c:choose>
								<c:when test="${request.isApproved}">
									<td><img src="static/images/accept.png" alt="accpect image" class="accept-image"></td>
									<td>${request.price}</td>
								</c:when>
								<c:otherwise>
									<td><img src="static/images/pending.png" alt="pending image" class="pending-image"></td>
									<td id="dash"><h1>-</h1></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				<table class="page-navigation">
					<tr>
						<c:forEach begin="1" end="${numberOfPages}" var="i">
							<td><h2>
									<a href="controller?command=requestsPage&page=${i}">${i}</a>
								</h2></td>
						</c:forEach>
					</tr>
				</table>
				<a href="controller?command=showPage&page=index"> <input type="button" id="main-page-btn"
						value="<fmt:message
                            key="main-page-btn"  />"></a>
			</div>
		</c:otherwise>
	</c:choose>
</html>