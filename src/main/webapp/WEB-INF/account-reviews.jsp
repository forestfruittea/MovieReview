<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 06.12.2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Reviews</title>
    <style>
        .review-container {
            display: flex;
            flex-direction: column;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin: 15px;
            padding: 10px;
            background-color: #f9f9f9;
        }
        .review-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }
        .review-header img {
            max-width: 100px;
            max-height: 150px;
            margin-right: 10px;
        }
        .movie-details {
            flex: 1;
        }
        .review-content {
            margin-top: 10px;
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
<h1>My Reviews</h1>
<c:forEach items="${reviews}" var="review">
    <div class="review-container">
        <div class="review-header">
            <img src="${review.movie.fullPosterPath}" alt="${review.movie.title} Poster">
            <div class="movie-details">
                <h2>${review.movie.title}</h2>
                <p>Release Date: ${review.movie.releaseDate}</p>
            </div>
            <div>
                <p>${review.createdAt}</p>
            </div>
        </div>
        <div class="review-content">
                ${review.content}
        </div>
    </div>
</c:forEach>
</body>
</html>

