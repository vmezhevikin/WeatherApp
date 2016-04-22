<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link href="css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<h2>Login</h2>
		<form action="/WeatherApp/login" method="post">
			User:<input name="username" class="form-control" />
			Password:<input name="password" type="password" class="form-control" />
			<br>
			<button name="action" value="login" class="btn btn-primary btn-block">Login</button>
			<br>
			<button name="action" value="signin" class="btn btn-warning btn-block">Sign in</button>
		</form>
	</div>
	<br><br><br><br><br><br><br><br><br>
	<p><small>© 2016 Viktor Mezhevikin</small></p><br>
	<p><small>Thanks:</small></p>
	<p><small>lections: Jose Annunziato</small></p>
	<p><small>weather API: worldweatheronline.com</small></p>
	<p><small>css: getbootstrap.com</small></p>
	<p><small>json parser: com.googlecode.json-simple</small></p>
</body>
</html>