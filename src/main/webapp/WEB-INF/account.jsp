<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Account</title>
    <style>
        /* Global styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fb;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            font-size: 2.5rem;
            color: #2c3e50;
        }

        .container {
            width: 80%;
            max-width: 1000px;
            margin: 0 auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            margin-top: 40px;
            text-align: center;
        }

        .user-avatar {
            border-radius: 20%;
            border: 4px solid #3498db;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            margin-bottom: 30px;
        }

        .user-details {
            font-size: 1.2rem;
            line-height: 1.8;
            margin-bottom: 30px;
        }

        .user-details p {
            font-size: 1.1rem;
            color: #555;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        ul li {
            display: inline-block;
            margin: 10px;
        }

        ul li a {
            text-decoration: none;
            background-color: #3498db;
            color: white;
            padding: 12px 24px;
            border-radius: 25px;
            font-size: 1.1rem;
            transition: background-color 0.3s ease;
        }

        ul li a:hover {
            background-color: #2980b9;
        }

        .footer {
            text-align: center;
            margin-top: 40px;
            font-size: 0.9rem;
            color: #777;
        }

        .footer a {
            color: #3498db;
            text-decoration: none;
        }

        /* Logout button styles */
        .logout-btn {
            text-decoration: none;
            background-color: #e74c3c;
            color: white;
            padding: 12px 24px;
            border-radius: 25px;
            font-size: 1.1rem;
            transition: background-color 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome, ${user.username}!</h1>
    <img class="user-avatar" src="${user.fullAvatarPath}" alt="User Avatar" width="150" height="150" />


    <ul>
        <li><a href="${pageContext.request.contextPath}/account/reviews">My Reviews</a></li>
        <c:if test="${user.role != 'CUSTOMER'}">
            <li><a href="${pageContext.request.contextPath}/admin/tool">Admin Tool</a></li>
        </c:if> <!-- Optional link for account settings -->
        <li><a class="logout-btn" href="${pageContext.request.contextPath}/logout">Logout</a></li> <!-- Logout button -->
    </ul>

</div>

</body>
</html>
