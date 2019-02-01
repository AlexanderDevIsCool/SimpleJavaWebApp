<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>WebApp</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<h1>This is the home page</h1>
	<form role="form" action="PostServ" method="post" onsubmit="return validate(this);">
		<div class="form=group">
			<label for="post">Create new post (141 char)</label>
			<textarea name="posttext" id="posttext" class="form=control" rows="2"
			 placeholder="Express yourself!!" maxlength="141"></textarea>
			 <div id="textarea_feedback"></div>	
		</div>
		<div class="form-group">
			<input type="submit" value="Submit" id="submit" />
			<input type="reset" value="Clear" />
		</div>
	</form>
<jsp:include page="footer.jsp"></jsp:include>
<script>
	$(document).ready(function(){
		var text_max = 141;
		$("#textarea_feedback").html(text_max + 'characters remaining');
		$('#posttext').keyup(function(){
			var text_length = $('#posttext').val().length;
			var text_remaining = text_max - text_length;
			
			$('#textarea_feedback').html(text_remaining + 'characters remaining');
		})
	});
	
	function validate(form){
		valid = true;
		if($('#posttext').val().length==0){
			alert("You may not submit an empty post.");
			valid = false;
		}
		return valid;
	}
</script>
</body>
</html>