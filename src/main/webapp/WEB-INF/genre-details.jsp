<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07.12.2024
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${genre.name} - Details</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h1 {
            text-align: center;
            margin: 20px 0;
        }

        /* Genre container */
        .genre-container {
            display: flex;
            justify-content: space-between;
            padding: 20px;
            margin: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .genre-image {
            width: 50%;
            height: auto;
            object-fit: cover;
            border-radius: 8px;
        }

        .genre-info {
            width: 45%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding-left: 20px;
        }

        .genre-info h2 {
            font-size: 32px;
            margin: 0;
            color: #333;
        }

        .genre-description {
            margin-top: 10px;
            font-size: 18px;
            color: #555;
        }

        .movie-list {
            display: flex;
            flex-direction: column;
            padding: 20px;
            margin-left: 20px;
        }

        .movie {
            display: flex;
            background-color: white;
            padding: 15px;
            margin: 10px 0;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 700px; /* Adjust card width */
            text-align: left;
        }

        .movie:hover{
            background-color: silver;
            transform: scale(1.05);
        }

        .movie-poster {
            width: 150px;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 15px;
        }

        .movie-details {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 100%;
        }

        .movie h2 {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            margin: 0;
        }

        .movie-info {
            font-size: 14px;
            color: #555;
        }

        .movie-info span {
            font-weight: bold;
            color: #333;
        }

        .movie-info p {
            margin: 5px 0;
        }
    </style>
</head>
<body>

<h1>Genre: ${genre.name}</h1>

<!-- Genre Info Section -->
<div class="genre-container">
    <!-- Genre Image -->
    <img class="genre-image" src="${genre.fullImagePath}" alt="${genre.name} Image">

    <!-- Genre Information -->
    <div class="genre-info">
        <h2>${genre.name}</h2>
        <p class="genre-description">${genre.description}</p>
    </div>
</div>

<!-- Movie List Section -->
<h2>Movies in this Genre</h2>
<div class="movie-list">
    <c:forEach var="movies" items="${movies}">
        <a href="${pageContext.request.contextPath}/movie?id=${movies.id}" class="movie">
            <div class="movie">
                <!-- Movie Poster -->
                <c:if test="${not empty movies.fullPosterPath}">
                    <img class="movie-poster" src="${movies.fullPosterPath}" alt="${movies.title} Poster"/>
                </c:if>

                <!-- Movie Details -->
                <div class="movie-details">
                    <!-- Title and Main Info -->
                    <h2>${movies.title}, ${movies.releaseDate}, ${movies.length} min</h2>

                    <!-- Additional Info -->
                    <div class="movie-info">
                        <!-- Genres -->
                        <p>${movies.country} •
                            <c:if test="${not empty movies.genres}">
                                <c:forEach var="genre" items="${movies.genres}" varStatus="status">
                                    ${genre.name}
                                    <c:if test="${!status.last}">,  </c:if>
                                </c:forEach>
                            </c:if>
                        </p>
                        <!-- Director -->
                        <p><span>Director:</span> ${movies.director.name}</p>

                        <!-- Starring Roles -->
                        <p><span>Starring roles:</span>
                            <c:if test="${not empty movies.actors}">
                                <c:forEach var="actor" items="${movies.actors}" varStatus="status">
                                    ${actor.name}
                                    <c:if test="${!status.last}">,  </c:if>
                                </c:forEach>
                            </c:if>
                        </p>
                    </div>
                </div>
            </div>
        </a>
    </c:forEach>
</div>

</body>
</html>

