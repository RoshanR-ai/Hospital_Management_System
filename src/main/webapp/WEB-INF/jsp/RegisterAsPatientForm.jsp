<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

.error{
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

.form-group input,
.form-group textarea {
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
<title>Register as Patient</title>
</head>
<body>
    <div class="form-container">
        <h1>Add Patient</h1>
        <form action="/register/addPatient" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" pattern="[a-zA-Z0-9]{3,}" title="Username must be at least 3 characters long and contain only letters and numbers" required />
                <c:if test="${param.error}">
                    <p class="error">Username is already taken!</p>
                </c:if>
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
                <label for="patientName">Name:</label>
                <input type="text" id="patientName" name="patientName" pattern="[A-Za-z ]{2,}" title="Name must be at least 2 characters long and contain only letters" required />
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Date of Birth:</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" required />
            </div>
            <div class="form-group">
                <label for="gender">Gender:</label>
                <input type="radio" id="male" name="gender" value="Male" required />
                <label for="male">Male</label>
                <input type="radio" id="female" name="gender" value="Female" required />
                <label for="female">Female</label>
                <input type="radio" id="other" name="gender" value="Other" required />
                <label for="other">Other</label>
            </div>
            <div class="form-group">
                <label for="contactNumber">Contact Number:</label>
                <input type="text" id="contactNumber" name="contactNumber" pattern="[0-9]{10}" title="Contact number must be 10 digits" required />
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required />
            </div>
            <button type="submit" class="submit-button">Register as a Patient</button>
        </form>
    </div>
</body>
</html>
