<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
/* Your existing CSS styles */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.form-container {
    background-color: #fff;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 400px;
    border-radius: 8px;
}

h1 {
    text-align: center;
    color: #333;
}

.form-group {
    margin-bottom: 15px;
    text-align: left;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    color: #555;
}

.form-group input,
.form-group textarea {
    width: calc(100% - 22px);
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.submit-button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}

.submit-button:hover {
    background-color: #0056b3;
}
</style>
<title>Update Patient</title>
</head>
<body>
    <div class="form-container">
        <h1>Update Patient</h1>
        <form action="${pageContext.request.contextPath}/admin/managePatients/updatePatient" method="post">
            <input type="hidden" id="patientId" name="patientId" value="${patient.patientId}" />

            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${patient.name}" required />
            </div>

            <div class="form-group">
                <label for="dateOfBirth">Date of Birth:</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" value="${patient.dateOfBirth}" required />
            </div>

            <div class="form-group">
                <label for="gender">Gender:</label>
                <input type="text" id="gender" name="gender" value="${patient.gender}" required />
            </div>

            <div class="form-group">
                <label for="contactNumber">Contact Number:</label>
                <input type="text" id="contactNumber" name="contactNumber" value="${patient.contactNumber}" required />
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${patient.address}" required />
            </div>

            <div class="form-group">
                <label for="medicalHistory">Medical History:</label>
                <textarea id="medicalHistory" name="medicalHistory" required>${patient.medicalHistory}</textarea>
            </div>

            <button type="submit" class="submit-button">Update Patient</button>
        </form>
    </div>
</body>
</html>
