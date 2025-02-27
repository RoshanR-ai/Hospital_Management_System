<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Already LoggedIn</title>
</head>
<body>
<h2>you are already logged In,${currentUser.userName}</h2>
<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
<button onclick="window.history.back()">Back</button>
</body>
</html>