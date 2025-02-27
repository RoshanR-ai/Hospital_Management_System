<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        h2 {
            text-align: center;
        }
        .detail {
            margin-top: 10px;
            padding: 10px;
            border: 1px solid #ccc;
           : 5px;
        }
        .detail label {
            font-weight: bold;
        }
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Patient Details</h2>
        <div class="detail">
            <label>Name:</label>
            <div>${patient.patientName}</div>
        </div>
        <div class="detail">
            <label>Date of Birth:</label>
            <div>${patient.dateOfBirth}</div>
        </div>
        <div class="detail">
            <label>Gender:</label>
            <div>${patient.gender}</div>
        </div>
        <div class="detail">
            <form action="/doctor/updateMedicalHistory/update" method="post">
                <label>Medical History:</label>
                <input name="appointmentId" value="${appointmentId}" type="hidden">
                <input name="patientId" value="${patient.patientId}" type="hidden">
                <textarea id="medicalHistory" name="medicalHistory" rows="10">${patient.medicalHistory}</textarea>
                <input type="submit" value="Update Medical History">
            </form>
        </div>
    </div>
</body>
</html>