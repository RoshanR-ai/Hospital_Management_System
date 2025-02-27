<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
    body {
        font-family: 'Poppins', sans-serif;
        background-repeat: no-repeat;
        background-size: cover;
        margin: 0;
        padding: 20px;
    }

    h2 {
        color: #333;
        text-align: center;
        margin-bottom: 20px;
        font-size: 24px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    th, td {
        padding: 10px;
        border: 1px solid #ddd;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    a {
        display: inline-block;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        text-align: center;
        transition: background-color 0.3s ease;
    }

    a:hover {
        background-color: #45a049;
    }

    .back-button {
        display: inline-block;
        padding: 10px 20px;
        background-color: #ea9c73;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        text-align: center;
        transition: background-color 0.3s ease;
        margin-top: 20px;
    }

    .back-button:hover {
        background-color: #848d97;
    }
</style>
<meta charset="UTF-8">
<title>Manage Doctors</title>
</head>
<body>
    <h1>List of Doctors</h1>
    <a href="${pageContext.request.contextPath}/admin/manageDoctors/addDoctor">Add New Doctor</a>
    <table border="1">
        <thead>
            <tr>
                <th>Doctor ID</th>
                <th>Doctor Name</th>
                <th>Specialization</th>
                <th>Contact Number</th>
                <th>Availability Schedule</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="doctor" items="${doctors}">
                <tr>
                    <td>${doctor.doctorId}</td>
                    <td>${doctor.doctorName}</td>
                    <td>${doctor.specialization}</td>
                    <td>${doctor.contactNumber}</td>
                    <td>${doctor.availabilitySchedule}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/manageDoctors/updateDoctor/${doctor.doctorId}">Edit</a>
                        <a href="${pageContext.request.contextPath}/admin/manageDoctors/deleteDoctor/${doctor.doctorId}" onclick="return confirm('Deleting this patient will also delete all associated appointments and bills associated with appointments. Are you sure you want to proceed?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
