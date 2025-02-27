<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Past Appointments</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Past Appointments</h2>
    <table>
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Appointment Date</th>
                <th>Doctor Name</th>
                <th>Time Slot</th>
                <th>Status</th>
                <tbody>
            <c:forEach var="appointment" items="${appointments}">
                <tr>
                    <td>${appointment.appointmentId}</td>
                    <td>${appointment.appointmentDate}</td>
                    <td>${appointment.doctor.doctorName}</td>
                    <td>${appointment.timeSlot}</td>
                    <td>${appointment.status}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty appointments}">
                <tr>
                    <td colspan="5">No past appointments found.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>