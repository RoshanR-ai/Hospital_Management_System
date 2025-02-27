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

    <title>Manage Patients</title>
</head>
<body>
    <h1>Manage Patients</h1>
    <a href="${pageContext.request.contextPath}/admin/managePatients/addPatient">Add New Patient</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>Contact Number</th>
                <th>Address</th>
                <th>Medical History</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="patient" items="${patients}">
                <tr>
                    <td>${patient.patientId}</td>
                    <td>${patient.patientName}</td>
                    <td>${patient.dateOfBirth}</td>
                    <td>${patient.gender}</td>
                    <td>${patient.contactNumber}</td>
                    <td>${patient.address}</td>
                    <td>${patient.medicalHistory}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/managePatients/updatePatient/${patient.patientId}">Update</a>
                        <a href="${pageContext.request.contextPath}/admin/managePatients/deletePatient/${patient.patientId}" onclick="return confirm('Deleting this patient will also delete all associated appointments and bills. Are you sure you want to proceed?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
