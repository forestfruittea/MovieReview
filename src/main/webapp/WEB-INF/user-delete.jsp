<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 10.12.2024
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .delete-container {
            margin-top: 20px;
            text-align: center;
        }
        .delete-container input[type="number"] {
            padding: 5px;
            font-size: 16px;
            width: 100px;
            margin-right: 10px;
        }
        .delete-container button {
            padding: 10px 20px;
            background-color: #e74c3c;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .delete-container button:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>All Users</h1>
    <div class="delete-container">
        <form action="${pageContext.request.contextPath}/admin/tool/users/delete" method="POST">
            <input type="number" name="userId" placeholder="Enter User ID" required>
            <button type="submit">Delete User</button>
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <th>User ID</th>
            <th>Username</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

