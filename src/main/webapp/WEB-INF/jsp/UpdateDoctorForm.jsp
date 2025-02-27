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

.form-group input {
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
<title>Update Doctor</title>
</head>
<body>
    <div class="form-container">
        <h1>Update Doctor</h1>
        <form action="${pageContext.request.contextPath}/admin/manageDoctors/updateDoctor" method="post">
            <input type="hidden" id="doctorId" name="doctorId" value="${doctor.doctorId}" />

            <div class="form-group">
                <label for="doctorName">Name:</label>
                <input type="text" id="doctorName" name="doctorName" value="${doctor.doctorName}" required />
            </div>

            <div class="form-group">
                <label for="specialization">Specialization:</label>
                <input type="text" id="specialization" name="specialization" value="${doctor.specialization}" required />
            </div>

            <div class="form-group">
                <label for="contactNumber">Contact Number:</label>
                <input type="text" id="contactNumber" name="contactNumber" value="${doctor.contactNumber}" required />
            </div>

            <div class="form-group">
                <label for="availabilitySchedule">Availability Schedule:</label>
                <input type="text" id="availabilitySchedule" name="availabilitySchedule" value="${doctor.availabilitySchedule}" required />
            </div>

            <button type="submit" class="submit-button">Update Doctor</button>
        </form>
    </div>
</body>
</html>

