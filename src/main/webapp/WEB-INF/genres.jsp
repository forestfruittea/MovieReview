<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Genre List</title>
    <style>
        /* General Styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 40px auto;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }

        h1 {
            margin-bottom: 20px;
            font-size: 36px;
            font-weight: bold;
            color: #333;
            text-align: center;
        }

        /* Genre Grid Layout */
        .genre-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(220px, 1fr)); /* Flexible columns */
            gap: 20px; /* Adds space between genre items */
            margin-top: 20px;
            justify-items: center; /* Centers items within each grid cell */
        }

        .genre-item {
            width: 100%; /* Ensures it takes full width of its grid cell */
            height: 180px; /* Adjust height for better proportion */
            text-align: center;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            background-color: #ffffff; /* Keeps the genre item background white */
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .genre-item:hover {
            transform: scale(1.05);  /* Slightly enlarges the item on hover */
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2); /* Adds a hover shadow */
        }

        .genre-item img {
            width: 100%; /* Ensures the image takes the full width of the container */
            height: 130px; /* Fixed height for all images */
            object-fit: cover; /* Ensures the image covers the entire container without distortion */
            transition: opacity 0.3s ease;
        }

        .genre-item:hover img {
            opacity: 0.8;  /* Slight hover effect on the image */
        }

        .genre-item p {
            font-size: 18px; /* Font size for genre name */
            font-weight: bold;
            margin-top: 10px;
            color: #333;  /* Adjust text color for visibility */
            margin-bottom: 0; /* Removes default margin */
            height: 30px; /* Fixed height for genre name */
            line-height: 30px; /* Vertically centers the text */
            overflow: hidden; /* Ensures long names don't overflow */
        }

        .genre-item a {
            text-decoration: none;
            color: inherit;
        }

        /* Section styling */
        .section {
            margin-bottom: 40px;
        }

        .section-title {
            font-size: 28px;
            font-weight: bold;
            color: #333;
            margin-bottom: 15px;
            text-align: center;
        }

    </style>
</head>
<body>

<div class="container">
    <!-- Genre List Section -->
    <div class="genre-container">
        <c:forEach var="genre" items="${genres}">
            <div class="genre-item">
                <a href="${pageContext.request.contextPath}/genre?id=${genre.id}">
                    <img src="${genre.fullImagePath}" alt="${genre.name}">
                    <p>${genre.name}</p>
                </a>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
