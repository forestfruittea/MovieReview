
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
<h1>Create a New Actor</h1>
<div class="container">
    <form id="actorForm">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="bio">Bio:</label>
        <input type="text" id="bio" name="bio">

        <label for="yearOfBirth">Year of Birth:</label>
        <input type="number" id="yearOfBirth" name="yearOfBirth">

        <label for="photoPath">Photo Path:</label>
        <input type="text" id="photoPath" name="photoPath" required>

        <label for="height">Height (cm):</label>
        <input type="number" id="height" name="height">

        <button type="submit">Create Actor</button>
    </form>
</div>

<script>
    document.getElementById('actorForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const actorData = {
            name: document.getElementById('name').value,
            bio: document.getElementById('bio').value,
            yearOfBirth: parseInt(document.getElementById('yearOfBirth').value),
            photoPath: document.getElementById('photoPath').value,
            height: parseInt(document.getElementById('height').value)
        };

        fetch('${pageContext.request.contextPath}/admin/tool/actors/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(actorData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else {
                    alert('Actor created successfully!');
                    document.getElementById('actorForm').reset();
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to create actor.');
            });
    });
</script>
</body>
</html>
