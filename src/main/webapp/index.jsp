<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(to bottom right, #3498db, #2ecc71);
            color: white;
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
            font-size: 2.5em;
        }

        .button {
            display: inline-block;
            background-color: #e74c3c;
            color: white;
            font-size: 1.2em;
            padding: 15px 30px;
            text-decoration: none;
            border-radius: 25px;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            margin: 10px;
        }

        .button:hover {
            background-color: #c0392b;
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        }

        .button.secondary {
            background-color: #3498db;
        }

        .button.secondary:hover {
            background-color: #2980b9;
        }

        .button.tertiary {
            background-color: #2ecc71;
        }

        .button.tertiary:hover {
            background-color: #27ae60;
        }
    </style>
</head>
<body>
<div>
    <h1>Welcome to the Movie App</h1>
    <a href="movies" class="button">Get Started As Guest!</a>
    <br />
    <a href="${pageContext.request.contextPath}/login" class="button secondary">Login</a>
    <a href="${pageContext.request.contextPath}/register" class="button tertiary">Register</a>
</div>
</body>
</html>
