<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Reviews</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin: 20px 0;
            color: #333;
        }

        .review-container {
            max-width: 90%;
            margin: 20px auto;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .review-header {
            display: flex;
            align-items: center;
            padding: 15px;
            border-bottom: 1px solid #ddd;
            background-color: #f7f7f7;
        }

        .review-header img {
            max-width: 80px;
            max-height: 120px;
            border-radius: 4px;
            margin-right: 15px;
        }

        .movie-details {
            flex: 1;
        }

        .movie-details h2 {
            margin: 0;
            font-size: 1.5em;
        }

        .movie-details h2 a {
            text-decoration: none;
            color: #0066cc;
        }

        .movie-details h2 a:hover {
            text-decoration: underline;
        }

        .movie-details p {
            margin: 5px 0;
            color: #555;
            font-size: 0.9em;
        }

        .review-header div:last-child {
            text-align: right;
            font-size: 0.8em;
            color: #999;
        }

        .review-content {
            flex: 1;
            padding: 15px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 15px;
            word-wrap: break-word;
        }

        .review-content p {
            font-size: 15px;
            color: #444;
            margin: 0;
        }
    </style>
</head>
<body>
<div>
    <c:forEach items="${reviews}" var="review">
        <div class="review-container">
            <div class="review-header">
                <img src="${review.movie.fullPosterPath}" alt="${review.movie.title} Poster">
                <div class="movie-details">
                    <h2><a href="/MovieRev-1.0-SNAPSHOT/movie?id=${review.movie.id}">${review.movie.title}</a></h2>
                    <p>Release Date: ${review.movie.releaseYear}</p>
                </div>
                <div>
                    <p>${review.formattedCreatedAt}</p>
                </div>
            </div>

            <div class="review-content">
                <p>${review.content}</p>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>