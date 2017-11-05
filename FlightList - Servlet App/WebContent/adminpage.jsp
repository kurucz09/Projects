<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	margin: 50px 100px;
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
	border-bottom: 2px solid #777777;
}

table th {
	border: none;
	border-top: 2px solid #777777;
	border-bottom: 2px solid #777777;
	color: white;
	background-color: #3e3e3e;
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
    right: 48px;
    width: 110px;
    background-color: #0f5628 !important;
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
	<form name="resetForm" method="POST" action="AdminPage">
		<input class="reset" type="submit" value="Reset Page"/>
	</form>
	<form name="adminForm" method="POST" action="AdminPage">
		<hr />
		<h2>Add new flight</h2>
			<label><h3>Flight number:</h3> <input type="text" required name="flightNumber" value="${requestScope.selected.flightNumber}" />
			</label> <label><h3>Airplane type:</h3> <input type="text" required name="airplaneType" value="${requestScope.selected.airplaneType}"/>
			</label> <label><h3>Departure city:</h3> <input type="text" required name="departureCity" value="${requestScope.selected.departureCity}"/>
			</label> <label><h3>Departure time:</h3> <input type="datetime-local" name="departureDatetime"  value="${requestScope.selected.departureDatetime}"/>
			</label> <label><h3>Arrival	city:</h3> <input type="text" required name="arrivalCity"  value="${requestScope.selected.arrivalCity}"/>
			</label> <label><h3>Arrival	time:</h3> <input type="datetime-local" name="arrivalDatetime"  value="${requestScope.selected.arrivalDatetime}"/></label>
		<div>
			<input type="submit" value="Add flight" name="btnAddFlight" /> 
			<input type="submit" value="Update flight" name="btnUpdateFlight" /> 
			<input type="reset" value="Clear" name="btnClearFlight" />
		</div>
		<p style="color:red">${requestScope.errorMessage}</p>
		<hr />
	</form>
	<h2>All flights</h2>
	<form name="tableForm" method="POST" action="editflight">
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
					<td><a href="editflight?index=${loop.count}">edit</a></td>
					<td><input type="hidden" name="index" value="${loop.count}"></td>
					<td><a href="deleteflight?delete=${loop.count}">delete</a></td>
					<td><input type="hidden" name="delete" value="${loop.count}"></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>