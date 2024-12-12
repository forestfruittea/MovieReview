<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 07.12.2024
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Directors List</title>
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

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        /* Directors List */
        .director-list {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .director-container {
            display: flex;
            flex-direction: row;
            gap: 20px;
            align-items: flex-start;
            padding: 15px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        .director-photo img {
            width: 200px;
            height: 300px;
            object-fit: cover;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        .director-info {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .director-name {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }

        .director-info p {
            margin: 5px 0;
            color: #555;
            font-size: 16px;
        }

        .bio {
            margin-top: 10px;
            font-size: 14px;
            color: #777;
            line-height: 1.6;
        }

        .movie-link {
            color: #007BFF;
            text-decoration: none;
            font-weight: bold;
            margin-top: 10px;
        }

        .movie-link:hover {
            text-decoration: underline;
        }

        /* Responsive Styling */
        @media (max-width: 768px) {
            .director-container {
                flex-direction: column;
                align-items: center;
            }

            .director-photo img {
                width: 100%;
                max-width: 300px;
                height: auto;
            }

            .director-info {
                align-items: center;
                text-align: center;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="director-list">
        <c:forEach var="director" items="${directors}">
            <div class="director-container">
                <!-- Director Photo -->
                <div class="director-photo">
                    <a href="${pageContext.request.contextPath}/director?id=${director.id}">
                        <img src="${director.fullPhotoPath}" alt="${director.name}">
                    </a>
                </div>

                <!-- Director Info -->
                <div class="director-info">
                    <a href="${pageContext.request.contextPath}/director?id=${director.id}" class="director-name">
                            ${director.name}
                    </a>
                    <c:if test="${not empty director.movies}">
                        <a href="${pageContext.request.contextPath}/movie?id=${director.movies[0].id}" class="movie-link">
                            Known for: ${director.movies[0].title} (${director.movies[0].releaseYear})
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
</div>
</body>
</html>
