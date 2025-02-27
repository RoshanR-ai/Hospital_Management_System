<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
        .dashboard-links {
            display: flex;
            flex-direction: column;
        }
        .dashboard-links a {
            text-decoration: none;
            color: #007BFF;
            font-size: 16px;
            margin: 10px 0;
        }
        .dashboard-links img {
            vertical-align: middle;
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <h2>Welcome to doctor dashboard, ${currentDoctor.doctorName}</h2>
    <div class="container">
        <div class="dashboard-links">
            <a href="/changePassword">Change Password</a>
            <a href="/doctor/generateAppointments">Generate Slots</a>
            <a href="/doctor/upcommingAppointments">Upcoming Appointments</a>
            <a href="/doctor/updateMedicalHistory">Update History</a>
            <a href="/doctor/completedConsultation">Completed Consultation</a>
            <a href="/logout">
                <img alt="" src="/images/logout.svg" width="16" height="16">
                Logout
            </a>
        </div>
    </div>
</body>
</html>