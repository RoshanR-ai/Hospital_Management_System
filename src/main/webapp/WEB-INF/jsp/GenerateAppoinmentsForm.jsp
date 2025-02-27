<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 20px;
}

h1 {
    color: #333;
}

form {
    margin-bottom: 20px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

label {
    display: block;
    margin-bottom: 10px;
    font-weight: bold;
}

input[type="date"],
input[type="submit"] {
    display: block;
    margin-bottom: 20px;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

input[type="submit"] {
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s;
}

input[type="submit"]:hover {
    background-color: #0056b3;
}

input[type="submit"]:active {
    background-color: #004494;
}

.slot-box {
    display: inline-block;
    padding: 10px;
    margin: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s, color 0.3s;
}

.slot-box input[type="checkbox"] {
    display: none;
}

.slot-box label {
    margin: 0;
    cursor: pointer;
    user-select: none; /* Prevent text selection */
}

.slot-box input[type="checkbox"]:checked + label {
    background-color: #007bff;
    color: #fff;
    border-radius: 5px;
    padding: 10px;
}

.slot-box label:focus {
    outline: none; /* Remove focus outline */
}

.slot-box label:active {
    transform: none; /* Prevent zooming */
    border: none; /* Prevent border generation */
}
</style>
</head>
<body>
<h1>Generate Appointment Time Slots for One Day</h1>
<form action="/doctor/generateAppointments/generate" method="post">
    <label>Select the date to generate appointments</label>
    <input type="date" name="DateToGenerate">
    <div>
        <input type="submit" value="Generate">
    </div>
</form>

<h1>Generate Appointment Time Slots for Date Range</h1>
<form action="/doctor/generateAppointments/generateForRange" method="post">
    <label>Select the Start Date</label>
    <input type="date" name="startDate">
    <label>Select the End Date</label>
    <input type="date" name="endDate">
    <div>
        <input type="submit" value="Generate">
    </div>
</form>

<h1>Generate Custom Appointment Dates</h1>
<form action="/doctor/generateAppointments/generateCustomSlots" method="post">
    <label>Select the Date</label>
    <input type="date" name="customDate" required>
    <c:forEach var="slot" items="${slots}">
        <div class="slot-box">
            <input type="checkbox" id="${slot}" name="selectedSlots" value="${slot}">
            <label for="${slot}">${slot}</label>
        </div>
    </c:forEach>
    <div>
        <input type="submit" value="Generate">
    </div>
</form>
</body>
</html>