<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <style>
        form {
            max-width: 400px;
            margin: auto;
            padding: 1em;
            border: 1px solid #ccc;
            border-radius: 1em;
        }
        div {
            margin-bottom: 1em;
        }
        label {
            margin-bottom: .5em;
            color: #333333;
            display: block;
        }
        input {
            border: 1px solid #CCCCCC;
            padding: .5em;
            font-size: 1em;
            width: 100%;
            button {
            padding: 0.7em;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 1em;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .message {
            margin-top: 1em;
            padding: 0.5em;
            border-radius: 0.5em;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>
    <h1>Change Password</h1>
    <form action="/changePassword/changePasswordAction" method="post">
        <div>
            <label for="currentPassword">Current Password:</label>
            <input type="password" id="currentPassword" name="currentPassword" required>
        </div>
        <div>
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" required>
        </div>
        <div>
            <label for="confirmPassword">Confirm New Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>
        <button type="submit">Change Password</button>
        <c:if test="${not empty error}">
            <div class="message success">${error}</div>
        </c:if>
    </form>
</body>
</html>