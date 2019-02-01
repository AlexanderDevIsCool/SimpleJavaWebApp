<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>
	<h1>Login</h1>
	
	<div class="contaier">
		<form class="form-signin" method="post" action="LoginServlet">
			<h2 class="form-signin-heading"> ${message} Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label>
			<input name="email" type="email" id="inputEmail" class="form-control"
			placeholder="Email address" required autofocus>
			<input type="hidden" name="action" id="action" value="login" />
			<label for="inputPassword" class="sr-only">Password</label>
			<input name="password" type="password" id="inputPassword"
			class="form-control" placeholder="Password" required>
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">
				Sign in
			</button>
		</form>
		
		<a href="adduser.jsp">Join</a>
	</div>
	
<jsp:include page="footer.jsp" />
</body>
</html>