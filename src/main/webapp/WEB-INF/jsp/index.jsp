<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hospital Management System</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #e9ecef;
        margin: 0;
        padding: 0;
    }

    nav {
        background-color: #007bff;
        color: #fff;
        padding: 10px 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    nav div {
        font-size: 24px;
    }

    .login-register-button a {
        color: #fff;
        text-decoration: none;
        padding: 10px 20px;
        background-color: #28a745;
        border-radius: 4px;
    }

    .login-register-button a:hover {
        background-color: #218838;
    }

    .content {
        padding: 20px;
        text-align: center;
    }

    .content h1 {
        color: #333;
    }

    .content p {
        color: #555;
        line-height: 1.6;
    }

    .nav-links {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .nav-links a {
        margin: 0 10px;
        color: #007bff;
        text-decoration: none;
        padding: 10px 20px;
        border: 1px solid #007bff;
        border-radius: 4px;
    }

    .nav-links a:hover {
        background-color: #007bff;
        color: #fff;
    }
</style>
</head>
<body>
<nav>
    <div>Hospital Management System</div>
    <div class="login-register-button">
        <a href="/login">Login</a>
        <a href="/register">Register as Patient</a>
    </div>
</nav>
<div class="content">
    <h1>Welcome to Our Hospital Management System</h1>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    <div class="nav-links">
        <a href="/about">About</a>
        <a href="/contact">Contact Us</a>
    </div>
</div>
</body>
</html>