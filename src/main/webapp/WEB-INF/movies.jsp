<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movies List</title>
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

        .movie-list {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
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

<h1>Movies List</h1>

<div class="movie-list">
    <c:forEach var="movie" items="${movies}">
        <a href="/MovieRev-1.0-SNAPSHOT/movie?id=${movie.id}" class="movie">
        <div class="movie">
            <!-- Movie Poster -->
            <c:if test="${not empty movie.fullPosterPath}">
                <img class="movie-poster" src="${movie.fullPosterPath}" alt="${movie.title} Poster"/>
            </c:if>

            <!-- Movie Details -->
            <div class="movie-details">
                <!-- Title and Main Info -->
                <h2>${movie.title}, ${movie.releaseDate}, ${movie.length} min</h2>

                <!-- Additional Info -->
                <div class="movie-info">
                    <!-- Genres -->
                    <p>${movie.country} •
                        <c:if test="${not empty movie.genres}">
                            <c:forEach var="genre" items="${movie.genres}" varStatus="status">
                                ${genre.name}
                                <c:if test="${!status.last}">,  </c:if>
                            </c:forEach>
                        </c:if>
                    </p>
                    <!-- Director -->
                    <p><span>Director:</span> ${movie.director.name}</p>

                    <!-- Starring Roles -->
                    <p><span>Starring roles:</span>
                        <c:if test="${not empty movie.actors}">
                            <c:forEach var="actor" items="${movie.actors}" varStatus="status">
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
