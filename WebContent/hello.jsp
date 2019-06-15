<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Salim's Weather Application</title>
</head>
<body>
	<form>
		<select>
			<option value="NoCity">Select</option>
			<c:forEach items="${cityList}" var="city">
        		<option value="${city.cityCode}">${city.cityName}</option>
    		</c:forEach>
		</select>
	</form>
	<table>
		<tr>
			<td>City</td><td></td>
		</tr>
		
		<tr>
			<td>Updated Time</td><td></td>
		</tr>
		
		<tr>
			<td>Weather</td><td></td>
		</tr>
		
		<tr>
			<td>Temperature</td><td></td>
		</tr>
		
		<tr>
			<td>Wind</td><td></td>
		</tr>
	</table>
	
</body>
</html>