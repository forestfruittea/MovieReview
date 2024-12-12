<%@ include file="/WEB-INF/header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Actor</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #444;
            margin-top: 20px;
        }

        .container {
            max-width: 800px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin: 20px auto 0;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        button {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #3498db;
            outline: none;
        }

        button {
            background-color: #3498db;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #2980b9;
        }

        .error {
            color: #e74c3c;
            font-size: 14px;
            margin-top: -10px;
            margin-bottom: 20px;
        }

        .success {
            color: #2ecc71;
            font-size: 14px;
        }

        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<h1>Create a New Actor</h1>

<!-- Display error messages -->
<c:if test="${not empty error}">
    <div style="color: red;">
        <pre>${error}</pre>
    </div>
</c:if>

<div class="container">
    <!-- Actor creation form -->
    <form method="post" action="${pageContext.request.contextPath}/admin/tool/actors/create" enctype="multipart/form-data">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${param.name}" required>

        <label for="bio">Bio:</label>
        <input type="text" id="bio" name="bio" value="${param.bio}">

        <label for="yearOfBirth">Year of Birth:</label>
        <input type="number" id="yearOfBirth" name="yearOfBirth" value="${param.yearOfBirth}">

        <label for="photoPath">Photo Path:</label>
        <input type="file" id="photoPath" name="photoPath" value="${param.photoPath}" required>

        <label for="height">Height (cm):</label>
        <input type="number" id="height" name="height" value="${param.height}">

        <button type="submit">Create Actor</button>
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/admin/tool/actors">Back to Actor List</a>
</div>
</body>
</html>
