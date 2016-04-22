<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.vemezhevikin.weather.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Weather</title>
<link href="css/bootstrap.css" rel="stylesheet" />
</head>
<body>

	<%
		String locationName = (String) request.getAttribute("locationname");
		Weather weather = (Weather) request.getAttribute("weather");
	%>

	<div class="container">

		<table class="table">
			<tr>
				<th><%=locationName%></th>
				<th><img
					src="<%=weather.getCurrentCondition().getWeatherIconUrl()%>"></th>
			</tr>
			<tr>
				<td>Observation time</td>
				<td><%=weather.getCurrentCondition().getDate()%></td>
			</tr>
			<tr>
				<td>Temperature</td>
				<td><%=weather.getCurrentCondition().getTempC()%> <%="\u00b0"%>C</td>
			</tr>
			<tr>
				<td>Humidity</td>
				<td><%=weather.getCurrentCondition().getHumidity()%> %</td>
			</tr>
			<tr>
				<td>Pressure</td>
				<td><%=weather.getCurrentCondition().getPressure()%> mm Hg</td>
			</tr>
			<tr>
				<td>Wind speed</td>
				<td><%=weather.getCurrentCondition().getWindspeedKmph()%> kmph</td>
			</tr>
			<tr>
				<td>Wind direction</td>
				<td><%=weather.getCurrentCondition().getWinddir16Point()%></td>
			</tr>
			<tr>
				<td>Weather description</td>
				<td><%=weather.getCurrentCondition().getWeatherDesc()%></td>
			</tr>
		</table>
	</div>
	<div class="container">
		<%
			for (int day = 0; day < 5; day++)
			{
		%>

		<table class="table">
			<tr>
				<th colspan="8"><%=weather.getForecast(day, 0).getDate()%></th>
			</tr>
			<tr>
				<%
					for (int time = 0; time < 8; time++)
						{
				%><td><%=weather.getForecast(day, time).getTime()%></td>
				<%
					}
				%>
			</tr>
			<tr>
				<%
					for (int time = 0; time < 8; time++)
						{
				%><td><img
					src="<%=weather.getForecast(day, time).getWeatherIconUrl()%>"></td>
				<%
					}
				%>
			</tr>
			<tr>
				<%
					for (int time = 0; time < 8; time++)
						{
				%><td><%=weather.getForecast(day, time).getTempC()%> <%="\u00b0"%>C</td>
				<%
					}
				%>
			</tr>
			<tr>
				<%
					for (int time = 0; time < 8; time++)
						{
				%><td><%=weather.getForecast(day, time).getHumidity()%> %</td>
				<%
					}
				%>
			</tr>
			<tr>
				<%
					for (int time = 0; time < 8; time++)
						{
				%><td><%=weather.getForecast(day, time).getPressure()%> mm Hg</td>
				<%
					}
				%>
			</tr>
			<tr>
				<%
					for (int time = 0; time < 8; time++)
						{
				%><td><%=weather.getForecast(day, time).getWindspeedKmph()%>
					kmph</td>
				<%
					}
				%>
			</tr>
			<tr>
				<%
					for (int time = 0; time < 8; time++)
						{
				%><td><%=weather.getForecast(day, time).getWinddir16Point()%></td>
				<%
					}
				%>
			</tr>
			<tr>
				<%
					for (int time = 0; time < 8; time++)
						{
				%><td><%=weather.getForecast(day, time).getWeatherDesc()%></td>
				<%
					}
				%>
			</tr>
		</table>

		<%
			}
		%>
	</div>

</body>
</html>