<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${movie.title}</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            margin: 0 auto;
            padding: 20px;
            max-width: 90%;
        }

        .movie-details {
            display: flex;
            flex-wrap: wrap;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .movie-poster {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 20px;
        }

        .movie-info {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .movie-title {
            font-size: 32px;
            font-weight: bold;
            margin: 0;
            color: #333;
        }

        .section-title {
            margin-top: 20px;
            font-size: 24px;
            font-weight: bold;
            color: #555;
        }

        .info-item {
            margin: 10px 0;
            font-size: 16px;
            color: #333;
        }

        .info-item span {
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="movie-details">
        <!-- Movie Poster -->
        <c:if test="${not empty movie.fullPosterPath}">
            <img class="movie-poster" src="${movie.fullPosterPath}" alt="${movie.title} Poster">
        </c:if>

        <!-- Movie Information -->
        <div class="movie-info">
            <!-- Title -->
            <h1 class="movie-title">${movie.title}</h1>

            <!-- About the Movie Section -->
            <h2 class="section-title">About the movie</h2>

            <!-- Movie Details -->
            <p class="info-item"><span>Release Date:</span> ${movie.releaseDate}</p>
            <p class="info-item"><span>Country:</span> ${movie.country}</p>
            <p class="info-item"><span>Genres:</span>
                <%--<c:forEach var="genre" items="${movie.genres}" varStatus="status">
                    ${genre.name}<c:if test="${!status.last}">, </c:if>
                </c:forEach>--%>
                <c:forEach var="genre" items="${movie.genres}" varStatus="status">
                    <a href="${pageContext.request.contextPath}/genre?id=${genre.id}" class="genre-link">${genre.name}</a>
                    <c:if test="${!status.last}">, </c:if>
                </c:forEach>
            </p>
            <p class="info-item"><span>Description:</span> ${movie.description}</p>
            <p class="info-item">
                <span>Director:</span>
                <a href="${pageContext.request.contextPath}/director?id=${movie.director.id}" class="director-link">
                    ${movie.director.name}
                </a>
            </p>
            <p class="info-item"><span>Budget:</span> $${movie.budget}</p>
            <p class="info-item"><span>Box Office:</span> $${movie.boxOffice}</p>
            <p class="info-item"><span>Starring Roles:</span>
                <c:forEach var="actor" items="${movie.actors}" varStatus="status">
                    <a href="${pageContext.request.contextPath}/actor?id=${actor.id}" class="actor-link">${actor.name}</a>
                    <c:if test="${!status.last}">, </c:if>
                </c:forEach>
            </p>
            <p class="info-item"><span>Duration:</span> ${movie.length} min</p>
        </div>
    </div>
    <h2>Reviews for ${movie.title}</h2>

    <!-- List of reviews -->
    <c:forEach var="review" items="${reviews}">
        <div>
            <img src="${review.user.fullAvatarPath}" alt="Avatar" width="50" height="50">
            <strong>${review.user.username}</strong> <em>${review.createdAt}</em>
            <p>${review.content}</p>
        </div>
    </c:forEach>

    <!-- Review form -->
    <form method="post" action="${pageContext.request.contextPath}/movie">
        <input type="hidden" name="id" value="${movie.id}">
        <textarea name="content" placeholder="Write your review..."></textarea>
        <button type="submit">Submit Review</button>
    </form>
</div>

</body>
</html>
