<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Search</title>
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
    <h1>All Movies</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="movie" items="${movies}">
            <tr>
                <td>${movie.id}</td>
                <td>${movie.title}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Delete Movie by ID -->
    <div class="delete-container">
        <form action="/MovieRev-1.0-SNAPSHOT/admin/movies/search" method="POST">
            <input type="number" name="movieId" placeholder="Enter Movie ID" required>
            <button type="submit">Delete Movie</button>
        </form>
    </div>
</div>
</body>
</html>
