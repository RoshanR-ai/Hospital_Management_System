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
}
.error {
            color: red;
            font-size: 12px;
        }

.form-container {
    width: 50%;
    margin: 50px auto;
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

h1 {
    text-align: center;
    color: #333;
}

.form-group {
    margin-bottom: 15px;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    color: #555;
}

.form-group input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.submit-button {
    width: 100%;
    padding: 10px;
    background-color: #28a745;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}

.submit-button:hover {
    background-color: #218838;
}
</style>
<title>Add New Doctor</title>
</head>
<body>
    <div class="form-container">
        <h1>Add New Doctor</h1>
        <form action="${pageContext.request.contextPath}/admin/manageDoctors/addDoctor/validateNewDoctor" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required />
                <p class="error">${errorMessage}</p>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required />
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required />
            </div>

            <div class="form-group">
                <label for="doctorName">Name:</label>
                <input type="text" id="doctorName" name="doctorName" required />
            </div>

            <div class="form-group">
                <label for="specialization">Specialization:</label>
                <input type="text" id="specialization" name="specialization" required />
            </div>

            <div class="form-group">
                <label for="contactNumber">Contact Number:</label>
                <input type="text" id="contactNumber" name="contactNumber" required />
            </div>

            <div class="form-group">
                <label for="availablitySchedule">Availablity Schedule:</label>
                <div>
                	<label>Start time:</label>
                	<input type="time" id="startTime" name="startTime" min="8:00" max="22:00" step="3600" required>
                	<label>End time:</label>
                	<input type="time" id="endTime" name="endTime" min="8:00" max="22:00" step="3600" required>
                </div>
            </div>

            <button type="submit" class="submit-button">Add Doctor</button>
        </form>
    </div>
</body>
</html>
