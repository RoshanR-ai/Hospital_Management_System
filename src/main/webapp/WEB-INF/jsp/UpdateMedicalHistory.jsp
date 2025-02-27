<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointments Needing Medical History Update</title>
</head>
<body>
    <h2>Appointments Needing Medical History Update</h2>
    <table border="1">
        <tr>
            <th>Appointment ID</th>
            <th>Patient Name</th>
            <th>Appointment Date</th>
            <th>Time Slot</th>
            <th>Gender</th>
        </tr>
        <c:forEach var="appointment" items="${appointments}">
            <tr>
                <td>${appointment.appointmentId}</td>
                <td>${appointment.patient.patientName}</td>
                <td>${appointment.appointmentDate}</td>
                <td>${appointment.timeSlot}</td>
                <td>${appointment.patient.gender}</td>
                <td><a href="/doctor/updateMedicalHistory/${appointment.appointmentId}">update History</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>