<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07.12.2024
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Directors List</title>
    <style>
        .director-list {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            padding: 20px;
            margin-left: 40px;
        }

        .director-container {
            display: flex;
            flex-direction: row;
            align-items: flex-start;
            width: 1000px;
            border: 1px solid #ddd;
            padding: 15px;
            margin: 10px 0;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .director-photo img {
            width: 300px;
            height: 400px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 15px;
        }

        .director-info {
            display: flex;
            flex-direction: column;
            flex: 1;
        }

        .director-info h2 {
            font-size: 28px;
            margin: 0 0 10px 0;
        }

        .director-info p {
            font-size: 20px;
            margin: 5px 0;
        }

        .director-info .bio {
            font-size: 16px;
            color: #555;
        }

        .director-info .movie {
            font-size: 20px;
            color: blue;
            text-decoration: underline;
        }

        .director-info .movie:hover {
            color: darkblue;
        }
    </style>
</head>
<body>
<h1>Directors</h1>
<div class="director-list">
    <c:forEach var="director" items="${directors}">
        <div class="director-container">
            <div class="director-photo">
                <h2>
                    <a href="${pageContext.request.contextPath}/director?id=${director.id}" class="director-link">
                        <img src="${director.fullPhotoPath}" alt="${director.name}">
                    </a>
                </h2>
            </div>
            <div class="director-info">
                <h2>
                    <a href="${pageContext.request.contextPath}/director?id=${director.id}" class="director-link">${director.name}</a>
                </h2>
                <c:if test="${not empty director.movies}">
                    <a href="${pageContext.request.contextPath}/movie?id=${director.movies[0].id}" class="movie">
                        <p>${director.movies[0].title} (${director.movies[0].releaseDate})</p>
                    </a>
                </c:if>
                <c:if test="${empty director.movies}">
                    <p>Known for: N/A</p>
                </c:if>
                <p>Date of Birth: ${director.dateOfBirth}</p>
                <p class="bio">${director.bio}</p>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>

