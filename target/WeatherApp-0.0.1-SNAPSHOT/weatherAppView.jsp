<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
  border: 1px dotted;
  border-collapse: collapse;
}
</style>
<meta charset="ISO-8859-1">
<title>Salim's Weather Application</title>
<script type="text/javascript">
	function submitForm(){
		var val = document.getElementById("cityDropdown").value;
		if("NoCity" != val){
			document.getElementById("form").submit();
		}
	}
</script>
</head>
<body>
	<form id="form" action="/WeatherApp/home" method="post">
		<select id="cityDropdown" name="cityDropdown" onchange="submitForm()">
			<option value="NoCity">Select</option>
			<c:forEach items="${cityList}" var="city">
        		<option value="${city.cityCode}">${city.cityName}</option>
    		</c:forEach>
		</select>
	</form>
	<table>
		<tr>
			<td>City</td><td>${owm.name}</td>
		</tr>
		
		<tr>
			<td>Updated Time</td><td>${owm.dt}</td>
		</tr>
		
		<tr>
			<td>Weather</td><td>${owm.weather[0].description}</td>
		</tr>
		
		<tr>
			<td>Temperature</td><td>${owm.main.temp}</td>
		</tr>
		
		<tr>
			<td>Wind</td><td>${owm.wind.speed}</td>
		</tr>
	</table>
	
</body>
</html>