<%@ include file="/WEB-INF/header.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Director</title>
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
<h1>Create a New Director</h1>
<div class="container">
    <form id="directorForm">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="bio">Bio:</label>
        <input type="text" id="bio" name="bio">

        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth">

        <label for="photoPath">Photo Path:</label>
        <input type="text" id="photoPath" name="photoPath" required>

        <button type="submit">Create Director</button>
    </form>
</div>

<script>
    document.getElementById('directorForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const directorData = {
            name: document.getElementById('name').value,
            bio: document.getElementById('bio').value,
            dateOfBirth: document.getElementById('dateOfBirth').value,
            photoPath: document.getElementById('photoPath').value
        };

        fetch('${pageContext.request.contextPath}/admin/tool/directors/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(directorData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else {
                    alert('Director created successfully!');
                    document.getElementById('directorForm').reset();
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to create director.');
            });
    });
</script>
</body>
</html>

