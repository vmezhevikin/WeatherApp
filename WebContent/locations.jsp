<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.vemezhevikin.weather.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Locations</title>
<link href="css/bootstrap.css" rel="stylesheet" />
</head>
<body>

	<%
		User user = (User) request.getAttribute("user");
		List<Location> locations = (List<Location>) request.getAttribute("locations");
	%>

	<h1><%=user.getName()%></h1>
	<h3>List of Locations</h3>

	<form action="/WeatherApp/location" method="post">
	<input type="hidden" name="userid" value=<%=user.getId()%>>
		<table class="table">
			<tr>
				<td><input name="locationname" class="form-control" /></td>
				<td>
					<button name="action" value="add" class="btn btn-success">Add</button>
				</td>
			</tr>
			<%
				for (Location location : locations)
				{
			%>
			<tr>
				<td><%=location.getName()%></td>
				<td><a
					href="/WeatherApp/weather?locationname=<%=location.getName()%>"
					class="btn btn-warning">Show</a></td>
				<td><a
					href="/WeatherApp/location?action=remove&locationid=<%=location.getId()%>&userid=<%=user.getId()%>"
					class="btn btn-danger">Remove</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<input type="hidden" name="userid" value=<%=user.getId()%>>
	</form>
</body>
</html>