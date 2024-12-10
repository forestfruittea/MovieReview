<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Genre List</title>
    <style>
        .genre-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
            margin: 20px;
            gap: 20px; /* Adds space between genre items */
        }

        .genre-item {
            width: 250px;  /* Adjust this width for each item */
            height: 220px;  /* Adjust this height for each item */
            text-align: center;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .genre-item img {
            width: 100%;
            height: 80%; /* Ensure the image takes up 70% of the genre item height */
            object-fit: cover;  /* Ensures the image covers the entire container */
        }

        .genre-item p {
            font-size: 18px; /* 15% smaller than the previous size */
            font-weight: bold;
            margin-top: 10px;
            color: #333;  /* Adjust text color for visibility */
            margin-bottom: 0; /* Removes the default margin to avoid unnecessary space */
            height: 30px; /* Set a fixed height for the genre name field */
            line-height: 30px; /* Vertically center the text in the field */
            overflow: hidden; /* Ensures the name does not overflow if it's too long */
        }

        .genre-item a {
            text-decoration: none;
            color: inherit;
        }

        .genre-item:hover img {
            opacity: 0.8; /* Add a slight hover effect on the image */
        }

        /* For mobile responsiveness */
        @media (max-width: 768px) {
            .genre-item {
                width: 180px;
                height: 160px;
            }

            .genre-item img {
                height: 60%; /* Adjust image size for smaller screens */
            }

            .genre-item p {
                font-size: 18px; /* Slightly smaller font size on tablet */
            }
        }

        @media (max-width: 480px) {
            .genre-item {
                width: 150px;
                height: 130px;
            }

            .genre-item img {
                height: 60%; /* Adjust image size for smaller screens */
            }

            .genre-item p {
                font-size: 18px; /* Smaller font size on mobile */
            }
        }
    </style>
</head>
<body>

<h1>Genres</h1>
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

</body>
</html>
