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
            margin-left: 40px;
        }

        .actor-container {
            display: flex;
            flex-direction: row; /* Align items horizontally */
            align-items: flex-start;
            width: 1000px;
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
            display: flex;
            flex-direction: column;
            flex: 1; /* Take up remaining space */
        }

        .actor-info h2 {
            font-size: 28px;
            margin: 0 0 10px 0;
        }

        .actor-info p {
            font-size: 20px;
            margin: 5px 0;
        }
        .actor-info .bio {
            font-size: 16px; /* Smaller font size for bio */
            color: #555; /* Optional: Slightly lighter color for bio */
        }

        .actor-info .movie {
            font-size: 20px;
            color: blue;
            text-decoration: underline;
        }

        .actor-info .movie:hover {
            color: darkblue;
        }
    </style>
</head>
<body>
<h1>Actors</h1>
<div class="actor-list">
    <c:forEach var="actor" items="${actors}">
        <div class="actor-container">
            <div class="actor-photo">
                <h2>
                    <a href="${pageContext.request.contextPath}/actor?id=${actor.id}" class="actor-link"><img src="${actor.fullPhotoPath}" alt="${actor.name}"></a>
                </h2>

            </div>
            <div class="actor-info">
                <h2>
                    <a href="${pageContext.request.contextPath}/actor?id=${actor.id}" class="actor-link">${actor.name}</a>
                </h2>
                <c:if test="${not empty actor.movies}">
                    <a href="${pageContext.request.contextPath}/movie?id=${actor.movies[0].id}" class="movie">
                        <p>${actor.movies[0].title} (${actor.movies[0].releaseDate})</p>
                    </a>
                </c:if>
                <c:if test="${empty actor.movies}">
                    <p>Known for: N/A</p>
                </c:if>
                <p>Height: ${actor.height} cm</p>
                <p>Year of Birth: ${actor.yearOfBirth}</p>
                <p class="bio">${actor.bio}</p>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
