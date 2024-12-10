<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07.12.2024
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>${director.name}</title>
    <style>
        .director-details {
            display: flex;
            flex-wrap: wrap;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 20px auto;
            max-width: 800px;
        }

        .director-photo {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 20px;
        }

        .director-info {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .director-name {
            font-size: 32px;
            font-weight: bold;
            margin: 0;
            color: #333;
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

<div class="director-details">
    <!-- Director Photo -->
    <img class="director-photo" src="${director.fullPhotoPath}" alt="${director.name} Photo">

    <!-- Director Information -->
    <div class="director-info">
        <h1 class="director-name">${director.name}</h1>
        <p class="info-item"><span>Date of Birth:</span> ${director.dateOfBirth}</p>
        <p class="info-item"><span>Bio:</span> ${director.bio}</p>

        <!-- Movies Directed by the Director -->
        <h2>Movies Directed</h2>
        <ul class="movies-list">
            <c:forEach var="movie" items="${director.movies}">
                <li>
                    <a href="${pageContext.request.contextPath}/movie?id=${movie.id}">
                        <img class="movie-poster" src="${movie.fullPosterPath}" alt="${movie.title} Poster">
                        <p class="info-item"> ${movie.title} (${movie.releaseDate})</p>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

</body>
</html>
