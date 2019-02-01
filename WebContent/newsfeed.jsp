<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US" />
<!-- fixes date not displaying correctly in Eclipse browser-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebApp</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<h1>This is the news feed page</h1>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>User</th>
					<th>Post</th>
					<th>Date</th>
				</tr>
			</thead>
			<c:forEach var="post" items="${posts}">
				<tr>
					<td><a
						href="ProfileServlet?action=viewprofile&userid=
					<c:out value="${post.user.id}"/>">
							<c:out value="${post.user.email}" />
					</a></td>
					<td><c:out value="${post.text}" /></td>
					<td><fmt:formatDate value="${post.date}" pattern="yy-MMM-dd" />
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>