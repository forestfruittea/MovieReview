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
    <title>${movie.title}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        .container {
            margin: 0 auto;
            padding: 20px;
            max-width: 1100px;
        }

        .section {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .section-title {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            margin-bottom: 15px;
        }

        .movie-details {
            display: flex;
            flex-wrap: wrap;
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
        }

        .info-item {
            margin: 10px 0;
            font-size: 16px;
            color: #333;
        }

        .info-item span {
            font-weight: bold;
        }

        .ratings-section {
            text-align: center;
        }

        .ratings-section h3 {
            margin-bottom: 10px;
        }

        form {
            margin-top: 20px;
        }

        textarea {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        button {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .review {
            display: flex;
            align-items: flex-start;
            padding: 20px 0; /* Increased padding for more space */
            border-bottom: 1px solid #ddd;
        }

        .review img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 20px;
        }

        .review-content {
            flex: 1;
            max-width: 90%; /* Ensures review content uses more space */
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .review-content strong {
            font-size: 16px;
            color: #333;
        }

        .review-content em {
            font-size: 14px;
            color: #888;
        }
        .review-content p {
            font-size: 15px;
            color: #444;
            word-wrap: break-word; /* Ensures the text wraps when it exceeds the line */
            margin-top: 10px;
        }

        /* Adjust the review section title */
        .section-title {
            font-size: 26px;
            font-weight: bold;
            color: #333;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- Movie Details Section -->
    <div class="section">
        <div class="movie-details">
            <c:if test="${not empty movie.fullPosterPath}">
                <img class="movie-poster" src="${movie.fullPosterPath}" alt="${movie.title} Poster">
            </c:if>
            <div class="movie-info">
                <h1 class="section-title">${movie.title}</h1>
                <p class="info-item"><span><fmt:message key="movieReleaseDate" />:</span> ${movie.releaseDate}</p>
                <p class="info-item"><span><fmt:message key="movieCountry" />:</span> ${movie.country}</p>
                <p class="info-item"><span><fmt:message key="movieGenres" />:</span>
                    <c:forEach var="genre" items="${movie.genres}" varStatus="status">
                        <a href="${pageContext.request.contextPath}/genre?id=${genre.id}" class="genre-link">${genre.name}</a>
                        <c:if test="${!status.last}">, </c:if>
                    </c:forEach>
                </p>
                <p class="info-item"><span><fmt:message key="movieDescription" />:</span> ${movie.description}</p>
                <p class="info-item"><span><fmt:message key="movieDirector" />:</span>
                    <a href="${pageContext.request.contextPath}/director?id=${movie.director.id}" class="director-link">${movie.director.name}</a>
                </p>
                <p class="info-item"><span><fmt:message key="movieBudget" />:</span> $${movie.budget}</p>
                <p class="info-item"><span><fmt:message key="movieBoxOffice" />:</span> $${movie.boxOffice}</p>
                <p class="info-item"><span><fmt:message key="movieStarringRoles" />:</span>
                    <c:forEach var="actor" items="${movie.actors}" varStatus="status">
                        <a href="${pageContext.request.contextPath}/actor?id=${actor.id}" class="actor-link">${actor.name}</a>
                        <c:if test="${!status.last}">, </c:if>
                    </c:forEach>
                </p>
                <p class="info-item"><span><fmt:message key="movieDuration" />:</span> ${movie.length} min</p>
            </div>
        </div>
    </div>

    <!-- Ratings Section -->
    <div class="section ratings-section">
        <h3><fmt:message key="averageRating" />: ${averageRating}</h3>
        <c:choose>
            <c:when test="${not empty sessionScope.userId}">
                <p>Your Rating: ${userRating != null ? userRating.rating : "Not Rated"}</p>
                <form action="${pageContext.request.contextPath}/ratings/rate" method="post">
                    <input type="hidden" name="movieId" value="${movie.id}">
                    <label for="rating">Rate the Movie (1-100):</label>
                    <input type="number" id="rating" name="rating" min="1" max="100"
                           value="${userRating != null ? userRating.rating : ''}" required>
                    <button type="submit">Submit Rating</button>
                </form>
            </c:when>

            <c:otherwise>
                <h3><fmt:message key="loginToRateMovies" />!</h3>
                <a href="${pageContext.request.contextPath}/index.jsp">
                    <button><fmt:message key="loginButton" /></button>
                </a>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Reviews Section -->
    <div class="section">
        <h2 class="section-title"><fmt:message key="reviewsForMovie" /> ${movie.title}</h2>
        <c:forEach var="review" items="${reviews}">
            <div class="review">
                <img src="${review.user.fullAvatarPath}" alt="Avatar">
                <div class="review-content">
                    <a href="${pageContext.request.contextPath}/userprofile?id=${review.user.id}"><strong>${review.user.username}</strong></a> <em>${review.formattedCreatedAt}</em>
                    <p>${review.content}</p>
                </div>
            </div>
        </c:forEach>

        <c:if test="${not empty sessionScope.userId}">
            <form action="${pageContext.request.contextPath}/reviews/add" method="post">
                <input type="hidden" name="movieId" value="${movie.id}">
                <textarea name="content" placeholder="Write your review..." required></textarea>
                <button type="submit">Submit Review</button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>