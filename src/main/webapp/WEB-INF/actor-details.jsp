<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07.12.2024
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${actor.name}</title>
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
            margin: 20px auto;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        h1, h2 {
            margin: 0 0 10px;
            color: #333;
        }

        .section {
            margin-bottom: 20px;
        }

        /* Actor Details */
        .actor-details {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }

        .actor-photo {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .actor-info {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .actor-name {
            font-size: 36px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .info-item {
            margin: 8px 0;
            font-size: 16px;
            color: #555;
        }

        .info-item span {
            font-weight: bold;
            color: #333;
        }

        /* Movies Section */
        .movies-section {
            border-top: 2px solid #f0f0f0;
            padding-top: 20px;
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
    </style>
</head>
<body>

<div class="container">
    <!-- Actor Section -->
    <div class="section actor-details">
        <img class="actor-photo" src="${actor.fullPhotoPath}" alt="${actor.name} Photo">
        <div class="actor-info">
            <h1 class="actor-name">${actor.name}</h1>
            <p class="info-item"><span>Height:</span> ${actor.height} cm</p>
            <p class="info-item"><span>Year of Birth:</span> ${actor.yearOfBirth}</p>
            <p class="info-item"><span>Bio:</span> ${actor.bio}</p>
        </div>
    </div>

    <!-- Movies Section -->
    <div class="section movies-section">
        <h2>Movies</h2>
        <ul class="movies-list">
            <c:forEach var="movie" items="${actor.movies}">
                <li class="movie-card">
                    <a href="${pageContext.request.contextPath}/movie?id=${movie.id}" class="movie-link">
                        <img class="movie-poster" src="${movie.fullPosterPath}" alt="${movie.title}">
                        <p class="movie-title">${movie.title} <span>(${movie.releaseDate})</span></p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

</body>
</html>
