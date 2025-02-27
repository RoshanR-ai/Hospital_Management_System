<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upcoming Appointments</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
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
 #f2f2f2;
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
    <div class="container">
        <h2>Upcoming Appointments</h2>
        <table>
            <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Patient Name</th>
                    <th>Appointment Date</th>
                    <th>Time Slot</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${upcommingAppointments}">
                    <tr>
                        <td>${appointment.appointmentId}</td>
                        <td>${appointment.patient.patientName}</td>
                        <td>${appointment.appointmentDate}</td>
                        <td>${appointment.timeSlot}</td>
                        <td>
                            <a href="/doctor/upcommingAppoitment/cancelAppointment?appointmentId=${appointment.appointmentId}">Cancel Appointment</a>

                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty upcommingAppointments}">
                    <tr>
                        <td colspan="5">No upcoming appointments found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</body>
</html>