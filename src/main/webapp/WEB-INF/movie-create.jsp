<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 10.12.2024
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Movie</title>
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
        input[type="date"],
        input[type="number"],
        input[type="file"],
        button {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        input[type="date"]:focus {
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
<head>
    <title>Create Movie</title>
</head>
<body>
<h1>Create a New Movie</h1>

<!-- Display error messages -->
<c:if test="${not empty error}">
    <div style="color: red;">
        <pre>${error}</pre>
    </div>
</c:if>

<!-- Movie creation form -->
<form method="post" action="${pageContext.request.contextPath}/admin/tool/movies/create" enctype="multipart/form-data">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${param.title}" required><br><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description" rows="4" cols="50">${param.description}</textarea><br><br>

    <label for="country">Country:</label>
    <input type="text" id="country" name="country" value="${param.country}"><br><br>

    <label for="directorName">Director Name:</label>
    <input type="text" id="directorName" name="directorName" value="${param.directorName}" required><br><br>

    <label for="actors">Actors (comma-separated):</label>
    <input type="text" id="actors" name="actors" value="${param.actors}" required><br><br>

    <label for="genres">Genres (comma-separated):</label>
    <input type="text" id="genres" name="genres" value="${param.genres}" required><br><br>

    <label for="releaseDate">Release Date (YYYY-MM-DD):</label>
    <input type="date" id="releaseDate" name="releaseDate" value="${param.releaseDate}" required><br><br>

    <label for="posterFile">Poster Image:</label>
    <input type="file" id="posterFile" name="posterFile" accept="image/*" required><br><br>

    <label for="length">Length (minutes):</label>
    <input type="number" id="length" name="length" value="${param.length}" required><br><br>

    <label for="budget">Budget (USD):</label>
    <input type="number" step="0.01" id="budget" name="budget" value="${param.budget}" required><br><br>

    <label for="boxOffice">Box Office (USD):</label>
    <input type="number" step="0.01" id="boxOffice" name="boxOffice" value="${param.boxOffice}" required><br><br>

    <button type="submit">Create Movie</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/admin/tool/movies">Back to Movie List</a>
</body>
</html>

