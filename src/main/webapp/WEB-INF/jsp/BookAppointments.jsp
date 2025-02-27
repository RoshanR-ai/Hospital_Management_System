<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        .doctor-box {
            border: 1px solid #ccc;
            padding: 15px;
            margin: 10px;
            border-radius: 5px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
        }
        .doctor-box h2 {
            margin-top: 0;
        }
        .doctor-box p {
            margin: 5px 0;
        }
        .view-slots {
            display: inline-block;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .view-slots:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Available Doctors</h1>
    <c:forEach var="doctor" items="${doctors}">
        <div class="doctor-box">
            <h2>${doctor.doctorName}</h2>
            <p><strong>Specialization:</strong> ${doctor.specialization}</p>
            <p><strong>Contact Number:</strong> ${doctor.contactNumber}</p>
            <p><strong>Availability Schedule:</strong> ${doctor.availabilitySchedule}</p>
            <a class="view-slots" href="/patient/bookAppointments/viewTimeSlots?doctorId=${doctor.doctorId}">View Slots</a>
        </div>
    </c:forEach>
</body>
</html>