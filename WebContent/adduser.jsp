<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WebApp</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>

	<form action="AddUser" method="post">
		<input type="hidden" name="action" value="addUser">
		<h1>Add New User</h1>
		<h2>
			Name: <input type="text" name="userName" value="" />
		</h2>
		<h2>
			Email: <input type="text" name="userEmail" value="" />
		</h2>
		<h2>
			Password: <input type="password" name="userPassword" value="" />
		</h2>
		<h2>
			Motto: <input type="text" name="userMotto" value="" />
		</h2>
		<input type="submit" value="Join Us" />
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>