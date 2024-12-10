<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        .container {
            width: 100%;
            max-width: 800px;
            margin: 40px auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 40px;
        }
        .edit-links {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .edit-links .edit-item {
            display: flex;
            justify-content: space-between;
            width: 80%;
            margin: 10px 0;
        }
        .edit-links a {
            text-decoration: none;
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s ease;
        }
        .edit-links a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Admin Dashboard</h1>
    <div class="edit-links">
        <!-- Edit Movies -->
        <div class="edit-item">
            <span>Edit Movies</span>
            <div>
                <a href="${pageContext.request.contextPath}/admin/tool/movies/create">Create</a>
                <a href="${pageContext.request.contextPath}/admin/tool/movies/delete">Delete</a>
            </div>
        </div>

        <!-- Edit Actors -->
        <div class="edit-item">
            <span>Edit Actors</span>
            <div>
                <a href="${pageContext.request.contextPath}/admin/tool/actors/create">Create</a>
                <a href="${pageContext.request.contextPath}/admin/tool/actors/delete">Delete</a>
            </div>
        </div>

        <!-- Edit Directors -->
        <div class="edit-item">
            <span>Edit Directors</span>
            <div>
                <a href="${pageContext.request.contextPath}/admin/tool/directors/create">Create</a>
                <a href="${pageContext.request.contextPath}/admin/tool/directors/delete">Delete</a>
            </div>
        </div>

        <!-- Edit Genres -->
        <div class="edit-item">
            <span>Edit Genres</span>
            <div>
                <a href="${pageContext.request.contextPath}/admin/tool/genres/create">Create</a>
                <a href="${pageContext.request.contextPath}/admin/tool/genres/delete">Delete</a>
            </div>
        </div>

        <div class="edit-item">
            <span>Edit Users</span>
            <div>
                <a href="${pageContext.request.contextPath}/admin/tool/users/create">Create</a>
                <a href="${pageContext.request.contextPath}/admin/tool/users/delete">Delete</a>
            </div>
        </div>

        <div class="edit-item">
            <span>Edit Reviews</span>
            <div>
                <a href="${pageContext.request.contextPath}/admin/tool/reviews/delete">Delete</a>
            </div>
        </div>


    </div>
</div>

</body>
</html>
