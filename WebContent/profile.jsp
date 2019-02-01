<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WebApp</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<c:choose>
		<c:when test="${editProfile==false}">
			<h1>
				<img src="${userimage}" alt=<c:out value="${username}" /> />
			</h1>
			<h2>
				Email:
				<c:out value="${useremail}" />
			</h2>
			<h2>
				Motto:
				<c:out value="${usermotto}" />
			</h2>
			<h2>
				Join Date:
				<c:out value="${userjointdate}" />
			</h2>
		</c:when>
		<c:when test="${editProfile==true}">
			<h1>
				<img src="#{userimage}" alt="${username}" />&nbsp;&nbsp; Edit
				profile for ${username}
			</h1>
			<form action="ProfileServlet" method="post">
				<input type="hidden" name="action" value="updateprofile" /> <input
					type="hidden" name="userid" value="${useremail}" />
				<h2>
					Email: <input type="text" name="useremail" value="${usermail}" />
				</h2>
				<h2>
					Motto: <input type="text" name="usermotto" value="${usermotto}" />
				</h2>
				<h2>
					Join Date:
					<c:out value="${userjoindate}" />
				</h2>
				<input type="submit" value="Save Changes" />
			</form>
		</c:when>
	</c:choose>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>






