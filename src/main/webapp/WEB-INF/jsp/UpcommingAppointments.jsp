<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upcoming Appointments</title>
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
        .cancel-button {
            background-color: #ff4d4d;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h2>Upcoming Appointments</h2>
    <table>
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Appointment Date</th>
                <th>Doctor Name</th>
                <th>Time Slot</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="appointment" items="${appointments}">
                <tr>
                    <td>${appointment.appointmentId}</td>
                    <td>${appointment.appointmentDate}</td>
                    <td>${appointment.doctor.doctorName}</td>
                    <td>${appointment.timeSlot}</td>
                    <td>
                        <a href="/patient/upcommingAppointments/cancel?appointmentId=${appointment.appointmentId}" onclick="return confirm('Are you sure you want to cancel this Appointment?');">Cancel Appointment</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty appointments}">
                <tr>
                    <td colspan="5">No upcoming appointments found.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>