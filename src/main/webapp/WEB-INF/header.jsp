<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="header">
    <div class="nav-left">
        <a href="${pageContext.request.contextPath}/movies">Movies</a>
        <a href="${pageContext.request.contextPath}/actors">Actors</a>
        <a href="${pageContext.request.contextPath}/directors">Directors</a>
        <a href="${pageContext.request.contextPath}/genres">Genres</a>
    </div>
    <div class="nav-right">
        <c:choose>
            <c:when test="${not empty sessionScope.userId}">
                <a href="${pageContext.request.contextPath}/account">Account</a>
            </c:when>

            <c:otherwise>
                <a href="${pageContext.request.contextPath}/index.jsp">Register</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<style>
    /* Header Styling */
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #1a1e3f; /* Very dark blue */
        padding: 15px 20px; /* Increase the padding for a larger header */
        color: white;
        height: 70px; /* Make the header height 2x bigger */
    }

    /* Left navigation styling */
    .nav-left a {
        margin: 0 20px; /* Increase space between links */
        text-decoration: none;
        color: white;
        font-size: 20px; /* Increase font size */
        font-weight: bold;
        transition: color 0.3s ease, text-decoration 0.3s ease;
    }

    /* Right navigation styling */
    .nav-right a {
        margin: 0 20px; /* Increase space between links */
        text-decoration: none;
        color: white;
        font-size: 20px; /* Increase font size */
        font-weight: bold;
        transition: color 0.3s ease, text-decoration 0.3s ease;
    }

    /* Hover effects */
    .nav-left a:hover, .nav-right a:hover {
        color: #4fa3f7; /* Change color to a soft blue */
        text-decoration: underline;
    }
</style>
