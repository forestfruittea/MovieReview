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

        h1, h2 {
            text-align: center;
            margin: 20px 0;
            color: #333;
        }

        /* Genre Info Container */
        .genre-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 30px;
            margin: 20px auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            max-width: 1200px;
        }

        .genre-image {
            width: 45%;
            height: 300px;
            object-fit: cover;
            border-radius: 8px;
        }

        .genre-info {
            width: 50%;
            padding-left: 20px;
        }

        .genre-info h2 {
            font-size: 32px;
            margin: 0;
            color: #333;
        }

        .genre-description {
            margin-top: 15px;
            font-size: 18px;
            color: #555;
        }

        /* Movies Section */
        .movies-section {
            margin: 40px auto;
            max-width: 1200px;
            padding: 30px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        .movies-list {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .movie-card {
            width: 150px;
            text-align: center;
            background: #fafafa;
            border: 1px solid #eaeaea;
            border-radius: 8px;
            padding: 10px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            flex-shrink: 0;
        }

        .movie-card:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
        }

        .movie-poster {
            width: 100%;
            height: 225px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        .movie-title {
            font-size: 14px;
            font-weight: bold;
            color: #333;
        }

        .movie-title span {
            color: #555;
            font-size: 12px;
            font-weight: normal;
        }

        .movie-link {
            color: inherit;
            text-decoration: none;
        }

        .movie-link:hover .movie-title {
            color: #007BFF;
        }

        /* Responsive Styling */
        @media (max-width: 768px) {
            .genre-container {
                flex-direction: column;
                align-items: center;
            }

            .genre-image {
                width: 80%;
                margin-bottom: 20px;
            }

            .genre-info {
                width: 100%;
                text-align: center;
            }

            .movies-list {
                justify-content: center;
            }

            .movie-card {
                width: 180px;
                margin: 10px;
            }

            .movie-poster {
                height: 180px;
            }
        }
    </style>
</head>
<body>

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

<!-- Movies Section -->
<div class="movies-section">
    <h2><fmt:message key="genreMovies" /></h2>
    <ul class="movies-list">
        <c:forEach var="movie" items="${movies}">
            <li class="movie-card">
                <a href="${pageContext.request.contextPath}/movie?id=${movie.id}" class="movie-link">
                    <!-- Movie Poster -->
                    <c:if test="${not empty movie.fullPosterPath}">
                        <img class="movie-poster" src="${movie.fullPosterPath}" alt="${movie.title} Poster"/>
                    </c:if>

                    <!-- Movie Title and Release Date -->
                    <p class="movie-title">${movie.title} <span>(${movie.releaseYear})</span></p>
                </a>
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
