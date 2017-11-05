<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight list</title>
<style>
html{
    padding: 30px 150px 30px 150px;
    height: 87%;
    background-color: #1267ad;
}
body {
	height: 100%;
    margin: 0!important;
    padding: 20px 50px;
    background-color: white;
}

input {
	height: 25px;
	width: 250px;
	margin: 10px 50px 10px 0;
}

input[type="submit"] {
	background-color: #3e3d3d;
	color: white;
	font-size: large;
	height: 40px;
	border: none;
}

input[type="reset"] {
	background-color: #b15656;
	color: white;
	font-size: large;
	height: 40px;
	border: none;
	float: right;
	margin-right: 0;
}

label {
	white-space: nowrap;
}

h3 {
	display: inline-block;
	width: 150px;
}

table {
	width: 100%;
	border: none;
	border-spacing: 0;
}

table tr {
	height: 40px;
	border-bottom: 2px solid #4f95d0;
}

table th {
	border: none;
	border-top: 2px solid #4f95d0;
	border-bottom: 2px solid #4f95d0;
	color: white;
	background-color: #1267ad;
}

table td {
	border: inherit;
	margin: 0;
	padding: 0;
	text-align: center;
}
.btn {
	width: 60px;
    margin: 10px 0;
    font-size: smaller !important;
}
.delete {
	background-color: #b15656 !important;
}
.reset{
	position: absolute;
    top: 47px;
    right: 150px;
    width: 110px;
    background-color: #1267ad !important;
}
td a{
    background-color: #3e3e3f;
    color: white;
    text-decoration: none;
    padding: 5px;
    margin: 2px;
}
</style>
</head>
<body>
	<form name="resetForm" method="POST" action="Homepage">
		<input class="reset" type="submit" value="Reset Page"/>
	</form>
	
	<p>Local departure time: ${requestScope.dateDeparture}  </p>
	<p>Local arrival time: ${requestScope.dateArrival}  </p>
	<h2>All flights</h2>
	<form name="tableForm" method="POST" action="querylocal">
		<table id="messages" border="1">
			<tr>
				<th>Flight number</th>
				<th>Airplane type</th>
				<th>Departure city</th>
				<th>Departure time</th>
				<th>Arrival city</th>
				<th>Arrival time</th>
			</tr>

			<c:forEach var="flight" items="${requestScope.flightList}"
				varStatus="loop">
				<tr>
					<td>${flight.flightNumber}</td>
					<td>${flight.airplaneType}</td>
					<td>${flight.departureCity}</td>
					<td>${flight.departureDatetime}</td>
					<td>${flight.arrivalCity}</td>
					<td>${flight.arrivalDatetime}</td>
					<td><a href="querylocal?index=${loop.count}">query local</a></td>
					<td><input type="hidden" name="index" value="${loop.count}"></td>
					
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>