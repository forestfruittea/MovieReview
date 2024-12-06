<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 06.12.2024
  Time: 16:42
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Actors List</title>
    <style>
        .actor-list {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            padding: 20px;
            margin-left: 20px;
        }

        .actor-container {
            display: flex;
            flex-direction: column;
            height: 550px;
            width: 300px;
            border: 1px solid #ddd;
            padding: 15px;
            margin: 10px 0;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .actor-photo img {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 15px;
        }

        .actor-info {
            margin-top: 10px;
        }

        .actor-info h2 {
            font-size: 28px;
            margin: 0;
        }

        .actor-info p {
            font-size: 20px;
            margin: 5px 0;
        }
    </style>
</head>
<body>
<h1>Actors</h1>
<div class="actor-list">
    <c:forEach var="actor" items="${actors}">
        <div class="actor-container">
            <div class="actor-photo">
                <img src="${actor.fullPhotoPath}" alt="${actor.name}">
            </div>
            <div class="actor-info">
                <h2>${actor.name}</h2>
                <c:if test="${not empty actor.movies}">
                <a href="/MovieRev-1.0-SNAPSHOT/movie?id=${actor.movies[0].id}" class="movie">
                    <p>${actor.movies[0].title} (${actor.movies[0].releaseDate})</p>
                </a>
                </c:if>
                <c:if test="${empty actor.movies}">
                    <p>Known for: N/A</p>
                </c:if>
                <p>Height: ${actor.height} cm</p>
                <p>Year of Birth: ${actor.yearOfBirth}</p>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
