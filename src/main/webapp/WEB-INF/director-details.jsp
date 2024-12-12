<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${director.name}</title>
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

        /* Director Details */
        .director-details {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }

        .director-photo {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .director-info {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .director-name {
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
    <!-- Director Section -->
    <div class="section director-details">
        <img class="director-photo" src="${director.fullPhotoPath}" alt="${director.name} Photo">
        <div class="director-info">
            <h1 class="director-name">${director.name}</h1>
            <p class="info-item"><span>Date of Birth:</span> ${director.dateOfBirth}</p>
            <p class="info-item"><span>Bio:</span> ${director.bio}</p>
        </div>
    </div>

    <!-- Movies Section -->
    <div class="section movies-section">
        <h2>Movies Directed</h2>
        <ul class="movies-list">
            <c:forEach var="movie" items="${director.movies}">
                <li class="movie-card">
                    <a href="${pageContext.request.contextPath}/movie?id=${movie.id}" class="movie-link">
                        <img class="movie-poster" src="${movie.fullPosterPath}" alt="${movie.title}">
                        <p class="movie-title">${movie.title} <span>(${movie.releaseYear})</span></p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

</body>
</html>
