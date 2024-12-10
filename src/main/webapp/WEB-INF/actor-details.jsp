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
        .actor-details {
            display: flex;
            flex-wrap: wrap;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 20px auto;
            max-width: 800px;
        }

        .actor-photo {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 20px;
        }

        .actor-info {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .actor-name {
            font-size: 32px;
            font-weight: bold;
            margin: 0;
            color: #333;
        }
        .movie-poster{
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 20px;
        }

        .info-item {
            margin: 10px 0;
            font-size: 16px;
            color: #333;
        }

        .info-item span {
            font-weight: bold;
        }

        .movies-list {
            list-style: none;
            padding: 0;
            margin: 10px 0 0 0;
        }

        .movies-list li {
            margin: 5px 0;
        }

        .movies-list a {
            color: blue;
            text-decoration: none;
        }

        .movies-list a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="actor-details">
    <!-- Actor Photo -->
    <img class="actor-photo" src="${actor.fullPhotoPath}" alt="${actor.name} Photo">

    <!-- Actor Information -->
    <div class="actor-info">
        <h1 class="actor-name">${actor.name}</h1>
        <p class="info-item"><span>Height:</span> ${actor.height} cm</p>
        <p class="info-item"><span>Year of Birth:</span> ${actor.yearOfBirth}</p>
        <p class="info-item"><span>Bio:</span> ${actor.bio}</p>

        <!-- Movies Section -->
        <h2>Movies</h2>
        <ul class="movies-list">
            <c:forEach var="movie" items="${actor.movies}">
                <li>
                    <a href="${pageContext.request.contextPath}/movie?id=${movie.id}">
                        <img class="movie-poster" src="${movie.fullPosterPath}"
                        <p class="info-item"> ${movie.title} (${movie.releaseDate})</p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

</body>
</html>

