<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actors List</title>
    <style>
        /* General Styles */
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

        /* Actors List */
        .actor-list {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .actor-container {
            display: flex;
            flex-direction: row;
            gap: 20px;
            align-items: flex-start;
            padding: 15px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        .actor-photo img {
            width: 200px;
            height: 300px;
            object-fit: cover;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        .actor-info {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .actor-name {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }

        .actor-info p {
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
            .actor-container {
                flex-direction: column;
                align-items: center;
            }

            .actor-photo img {
                width: 100%;
                max-width: 300px;
                height: auto;
            }

            .actor-info {
                align-items: center;
                text-align: center;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="actor-list">
        <c:forEach var="actor" items="${actors}">
            <div class="actor-container">
                <!-- Actor Photo -->
                <div class="actor-photo">
                    <a href="${pageContext.request.contextPath}/actor?id=${actor.id}">
                        <img src="${actor.photoPath}" alt="${actor.name}">
                    </a>
                </div>

                <!-- Actor Info -->
                <div class="actor-info">
                    <a href="${pageContext.request.contextPath}/actor?id=${actor.id}" class="actor-name">
                            ${actor.name}
                    </a>
                    <c:if test="${not empty actor.movies}">
                        <a href="${pageContext.request.contextPath}/movie?id=${actor.movies[0].id}" class="movie-link">
                            <fmt:message key="personKnownFor" />: ${actor.movies[0].title} (${actor.movies[0].releaseYear})
                        </a>
                    </c:if>
                    <c:if test="${empty actor.movies}">
                        <p>Known for: N/A</p>
                    </c:if>
                    <p><fmt:message key="personHeight" />: ${actor.height} cm</p>
                    <p><fmt:message key="personDateOfBirth" />: ${actor.yearOfBirth}</p>
                    <p class="bio">${actor.bio}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
