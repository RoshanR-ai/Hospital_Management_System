<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Dashboard</title>
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
        h3 {
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
        .dashboard-links a:hover {
        }
      </style>
     </head>
           <body>
    <div class="container">
        <h3>Welcome to the dashboard, ${currentPatient.patientName}</h3>
        <div class="dashboard-links">
            <a href="/changePassword">Change Password</a>
            <a href="/patient/bookAppointments">Book Appointments</a>
            <a href="/patient/upcommingAppointments">Upcoming Appointments</a>
            <a href="/patient/appointmentHistory">Appointment History</a>
            <a href="/patient/billing">Billing</a>
            <a href="/logout">Logout</a>
        </div>
    </div>
</body>
</html>