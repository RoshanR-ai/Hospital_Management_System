<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointments</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .form-group {
           -group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group select, .form-group button {
            padding: 8px;
            width: 100%;
            box-sizing: border-box;
        }
        .form-group button {
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <h2>Appointments</h2>
    <table>
        <thead>
            <tr>
                <th>Appointment ID</th>
                <th>Appointment Date</th>
                <th>Time Slot</th>
                <th>Patient Name</th>
                <th>Patient Contact Number</th>
                <th>Doctor Name</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="appointment" items="${appointments}">
                <tr>
                    <td><c:out value="${appointment.appointmentId}"/></td>
                    <td><c:out value="${appointment.appointmentDate}"/></td>
                    <td><c:out value="${appointment.timeSlot}"/></td>
                    <td><c:out value="${appointment.patient.patientName}"/></td>
                    <td><c:out value="${appointment.patient.contactNumber}"/></td>
                    <td><c:out value="${appointment.doctor.doctorName}"/></td>
                    <td><c:out value="${appointment.status}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>