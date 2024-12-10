<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 10.12.2024
  Time: 12:20
  To change this template use File | Settings | File Templates.

--%>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Genre</title>
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

        input {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
<h1>Create a New Genre</h1>
<div class="container">
    <form id="genreForm">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description">

        <label for="imagePath">Image Path:</label>
        <input type="text" id="imagePath" name="imagePath">

        <button type="submit">Create Genre</button>
    </form>
</div>

<script>
    document.getElementById('genreForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const genreData = {
            name: document.getElementById('name').value,
            description: document.getElementById('description').value,
            imagePath: document.getElementById('imagePath').value
        };

        fetch('${pageContext.request.contextPath}/admin/tool/genres/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(genreData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else {
                    alert('Genre created successfully!');
                    document.getElementById('genreForm').reset();
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to create genre.');
            });
    });
</script>
</body>
</html>

