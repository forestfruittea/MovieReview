<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp" %>
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

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .movie-list {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .movie-card {
            display: flex;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 15px;
            transition: background-color 0.3s, transform 0.3s;
            position: relative; /* To position the rating at the top-right corner */
        }

        .movie-card:hover {
            background-color: #e0e0e0;
            transform: scale(1.02);
        }

        .movie-poster {
            width: 150px;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 20px;
        }

        .movie-details {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .movie-title {
            font-size: 20px;
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

        /* Rating Styles */
        .rating {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #1a1e3f; /* Dark background for contrast */
            color: white;
            font-size: 2em; /* Large font size for emphasis */
            padding: 10px 15px;
            border-radius: 50%; /* Circular background */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            display: flex;
            align-items: center;
            justify-content: center;
            min-width: 50px;
            height: 50px;
            text-align: center;
        }
        .pagination {
            text-align: center;
            margin: 20px 0;
        }
        .pagination a {
            padding: 10px 15px;
            margin: 0 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-decoration: none;
            color: #333;
        }
        .pagination a:hover {
            background-color: #f0f0f0;
        }
        .pagination span {
            font-weight: bold;
            margin: 0 10px;
        }


    </style>
</head>
<body>


<div class="container">


    <div class="movie-list">
        <c:forEach var="movie" items="${movies}">
            <a href="${pageContext.request.contextPath}/movie?id=${movie.id}" class="movie-card">
                <!-- Movie Poster -->
                <c:if test="${not empty movie.fullPosterPath}">
                    <img class="movie-poster" src="${movie.fullPosterPath}" alt="${movie.title} Poster"/>
                </c:if>

                <!-- Movie Rating -->
                <c:if test="${not empty movie.averageRating}">
                    <div class="rating">
                        <fmt:formatNumber value="${movie.averageRating}" type="number" maxFractionDigits="1" />
                    </div>
                </c:if>

                <!-- Movie Details -->
                <div class="movie-details">
                    <!-- Title and Main Info -->
                    <h2 class="movie-title">${movie.title}, ${movie.releaseYear}, ${movie.length} min</h2>

                    <!-- Additional Info -->
                    <div class="movie-info">
                        <!-- Country and Genres -->
                        <p>${movie.country} &#8226;
                            <c:if test="${not empty movie.genres}">
                                <c:forEach var="genre" items="${movie.genres}" varStatus="status">
                                    ${genre.name}
                                    <c:if test="${!status.last}">, </c:if>
                                </c:forEach>
                            </c:if>
                        </p>

                        <!-- Director -->
                        <p><span><fmt:message key="movieDirector" />:</span> ${movie.director.name}</p>

                        <!-- Starring Roles -->
                        <p><span><fmt:message key="movieStarringRoles" />:</span>
                            <c:if test="${not empty movie.actors}">
                                <c:forEach var="actor" items="${movie.actors}" varStatus="status">
                                    ${actor.name}
                                    <c:if test="${!status.last}">, </c:if>
                                </c:forEach>
                            </c:if>
                        </p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</div>
<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/movies?page=${currentPage - 1}" class="prev"><fmt:message key="paginationPreviousButton" /></a>
    </c:if>

    <span><fmt:message key="paginationPage" /> ${currentPage} <fmt:message key="paginationOf" /> ${totalPages}</span>

    <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/movies?page=${currentPage + 1}" class="next"><fmt:message key="paginationNextButton" /></a>
    </c:if>
</div>
</body>
</html>
