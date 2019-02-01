<nav class="navbar navbar-default">

	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-traget="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
			</button>
			<img src="images/bullhornlogo50x50.png" alt="Bullhorn Logo" />&nbsp;
			<h2>WebApp</h2>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example=navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="home.jsp"> Home <span
						class="sr-only">(current)</span>
				</a></li>
				<li><a href="Newsfeed">News feed</a></li>
			</ul>

			<form class="navbar-form navbar-right" role="search"
				action="Newsfeed" method="get">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search"
						name="searchtext">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>

			<ul class="nav navbar-nav navbar-right">
				<%
					if (session.getAttribute("user") != null) {
				%>
				<li><a href="ProfileServlet?user=${user.id}&action=viewprofile">
						<img alt="${user.name}" src="${gravatarURL}" />&nbsp;
						${user.name}
				</a></li>
				<%
					}
				%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> User Options <span class="caret"></span>
				</a>
					<ul>
						<li>
							<form class="navbar-form navbar-left" role="form"
								action="LoginServlet" method="post">
								<input type="hidden" name="action" id="action" value="logout" />
								<button class="btn btn-default" id="addBookButton">Logout</button>
							</form>
						</li>
						<li role="serparator" class="divider"></li>
						<li><a href="support.jsp">Feedback</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>