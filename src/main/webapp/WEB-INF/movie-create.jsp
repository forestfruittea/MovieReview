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
<body>
<h1>Create a New Movie</h1>
<div class="container">
    <form id="movieForm">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
            <div class="error" id="titleError"></div>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" id="description" name="description">
            <div class="error" id="descriptionError"></div>
        </div>

        <div class="form-group">
            <label for="country">Country:</label>
            <input type="text" id="country" name="country">
            <div class="error" id="countryError"></div>
        </div>

        <div class="form-group">
            <label for="directorName">Director Name:</label>
            <input type="text" id="directorName" name="directorName" required>
            <div class="error" id="directorError"></div>
        </div>

        <div class="form-group">
            <label for="actors">Actors (comma separated):</label>
            <input type="text" id="actors" name="actors" required>
            <div class="error" id="actorsError"></div>
        </div>

        <div class="form-group">
            <label for="genres">Genres (comma separated):</label>
            <input type="text" id="genres" name="genres" required>
            <div class="error" id="genresError"></div>
        </div>

        <div class="form-group">
            <label for="releaseDate">Release Date:</label>
            <input type="date" id="releaseDate" name="releaseDate" required>
            <div class="error" id="releaseDateError"></div>
        </div>

        <div class="form-group">
            <label for="posterPath">Poster from base directory (filename only):</label>
            <input type="text" id="posterPath" name="posterPath" required>
            <div class="error" id="posterPathError"></div>
        </div>

        <div class="form-group">
            <label for="length">Length (in minutes):</label>
            <input type="number" id="length" name="length" required>
            <div class="error" id="lengthError"></div>
        </div>

        <div class="form-group">
            <label for="budget">Budget:</label>
            <input type="number" id="budget" name="budget" required>
            <div class="error" id="budgetError"></div>
        </div>

        <div class="form-group">
            <label for="boxOffice">Box Office:</label>
            <input type="number" id="boxOffice" name="boxOffice" required>
            <div class="error" id="boxOfficeError"></div>
        </div>

        <button type="submit">Create Movie</button>
        <div class="success" id="successMessage"></div>
    </form>
</div>

<script>
    // Handle form submission
    document.getElementById('movieForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission
        clearErrors(); // Clear previous error messages

        // Collect form data
        const movieData = {
            title: document.getElementById('title').value,
            description: document.getElementById('description').value,
            country: document.getElementById('country').value,
            director: {
                name: document.getElementById('directorName').value
            },
            actors: document.getElementById('actors').value.split(',').map(name => ({ name: name.trim() })),
            genres: document.getElementById('genres').value.split(',').map(name => ({ name: name.trim() })),
            releaseDate: document.getElementById('releaseDate').value,
            posterPath: document.getElementById('posterPath').value,
            length: parseInt(document.getElementById('length').value),
            budget: parseInt(document.getElementById('budget').value),
            boxOffice: parseInt(document.getElementById('boxOffice').value)
        };

        // Validate data
        const isValid = validateForm(movieData);
        if (!isValid) return;

        // Send the data as JSON to the server
        fetch('${pageContext.request.contextPath}/admin/tool/movies/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(movieData)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                document.getElementById('successMessage').textContent = 'Movie created successfully!';
                document.getElementById('movieForm').reset(); // Reset the form
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error creating movie');
            });
    });

    // Function to validate the form
    function validateForm(data) {
        let isValid = true;
        if (!data.title || data.title.length < 1 || data.title.length > 255) {
            showError('title', 'Title must be between 1 and 255 characters');
            isValid = false;
        }
        if (data.description && data.description.length > 500) {
            showError('description', 'Description must be at most 500 characters');
            isValid = false;
        }
        if (!data.director.name) {
            showError('director', 'Director name is required');
            isValid = false;
        }
        if (!data.actors.length) {
            showError('actors', 'At least one actor is required');
            isValid = false;
        }
        if (!data.genres.length) {
            showError('genres', 'At least one genre is required');
            isValid = false;
        }
        if (!data.releaseDate) {
            showError('releaseDate', 'Release date is required');
            isValid = false;
        }
        if (!data.posterPath) {
            showError('posterPath', 'Poster path is required');
            isValid = false;
        }
        if (data.length <= 0) {
            showError('length', 'Length must be a positive number');
            isValid = false;
        }
        if (data.budget <= 0) {
            showError('budget', 'Budget must be a positive number');
            isValid = false;
        }
        if (data.boxOffice <= 0) {
            showError('boxOffice', 'Box office must be a positive number');
            isValid = false;
        }
        return isValid;
    }

    // Function to display error messages
    function showError(field, message) {
        document.getElementById(field + 'Error').textContent = message;
    }

    // Function to clear error messages
    function clearErrors() {
        const errorFields = document.querySelectorAll('.error');
        errorFields.forEach(field => field.textContent = '');
    }
</script>
</body>
</html>

