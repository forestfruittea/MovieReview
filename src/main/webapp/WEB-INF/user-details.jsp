<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1, h3 {
            font-size: 1.8em;
            margin-bottom: 10px;
        }

        p {
            color: #555;
            font-size: 1em;
        }

        /* User Details Section */
        .user-details {
            text-align: center;
            margin-bottom: 40px;
        }

        .user-details img {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
            border: 3px solid #ddd;
        }

        .user-details h1 {
            margin-top: 15px;
            font-size: 2.2em;
            color: #333;
        }

        .user-details p {
            font-size: 1.1em;
            color: #888;
        }

        /* Movies Section */
        .movies {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 30px;
            margin-top: 20px;
        }

        .movie-card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-decoration: none; /* Remove underline */
        }

        .movie-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }

        .movie-card img {
            width: 100%;
            height: 300px;
            object-fit: cover;
            border-bottom: 1px solid #eee;
        }

        .movie-card .movie-info {
            padding: 15px;
            text-align: center;
        }

        .movie-card h3 {
            color: #333;
            font-size: 1.4em;
            margin-bottom: 10px;
        }

        .movie-card p {
            color: #777;
            font-size: 1em;
            margin-bottom: 15px;
        }

        .movie-card a {
            display: inline-block;
            padding: 8px 20px;
            background-color: #0066cc;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }

        .movie-card a:hover {
            background-color: #005bb5;
        }

    </style>
</head>
<body>
<div class="container">
    <!-- User Details -->
    <div class="user-details">
        <c:if test="${not empty user}">
            <img src="${user.fullAvatarPath}" alt="${user.username}'s Avatar">
            <h1>${user.username}</h1>
        </c:if>
    </div>

    <!-- Movies Rated by the User -->
    <div class="movies">
        <c:forEach items="${ratedMovies}" var="movie">
            <!-- Make the whole movie card clickable by wrapping it in an anchor tag -->
            <a href="${pageContext.request.contextPath}/movie?id=${movie.id}" class="movie-card">
                <img src="${movie.fullPosterPath}" alt="${movie.title} Poster">
                <div class="movie-info">
                    <h3>${movie.title}</h3>
                    <!-- Display the user's rating for this movie -->
                    <p>User's rate:
                        <c:if test="${not empty movieRatings[movie.id]}">
                            ${movieRatings[movie.id].rating}
                        </c:if>
                    </p>
                </div>
            </a>
        </c:forEach>
    </div>
</div>
</body>
</html>
