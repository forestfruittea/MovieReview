<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="i18n.messages" scope="session"/>
<c:out value="${sessionScope.locale}" />
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}" />

<html>

<!-- Navigation Bar -->
<nav class="header">
    <!-- Left Navigation -->
    <div class="nav-left">
        <a href="${pageContext.request.contextPath}/movies"><fmt:message key="headerMovies" /></a>
        <a href="${pageContext.request.contextPath}/actors"><fmt:message key="headerActors" /></a>
        <a href="${pageContext.request.contextPath}/directors"><fmt:message key="headerDirectors" /></a>
        <a href="${pageContext.request.contextPath}/genres"><fmt:message key="headerGenres" /></a>
    </div>

    <!-- Right Navigation -->
    <div class="nav-right">
        <c:choose>
            <c:when test="${not empty sessionScope.userId}">
                <a href="${pageContext.request.contextPath}/account"><fmt:message key="headerAccount" /></a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="headerRegister" /></a>
            </c:otherwise>
        </c:choose>

        <!-- Language Dropdown -->
        <div class="dropdown">
            <button class="dropbtn"><fmt:message key="headerLanguage" /></button>
            <div class="dropdown-content">
                <a href="?lang=en">English</a>
                <a href="?lang=ru">Русский</a>
            </div>
        </div>
    </div>
</nav>

<script>
    document.querySelectorAll('.dropdown-content a').forEach(function(element) {
        element.addEventListener('click', function(event) {
            event.preventDefault();
            const lang = this.href.split('=')[1];
            const url = new URL(window.location.href);
            url.searchParams.set('lang', lang);
            window.location.href = url.toString();
        });
    });
</script>

<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }

    /* Header Styling */
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #1a1e3f; /* Dark blue */
        padding: 15px 20px;
        color: white;
        height: 70px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    }

    /* Left Navigation Styling */
    .nav-left a {
        margin: 0 15px;
        text-decoration: none;
        color: white;
        font-size: 16px;
        font-weight: bold;
        transition: color 0.3s;
    }

    /* Right Navigation Styling */
    .nav-right {
        display: flex;
        align-items: center;
        gap: 15px;
    }

    .nav-right a {
        text-decoration: none;
        color: white;
        font-size: 16px;
        font-weight: bold;
        transition: color 0.3s;
    }

    /* Hover Effects */
    .nav-left a:hover,
    .nav-right a:hover {
        color: #4fa3f7; /* Soft blue */
    }

    /* Dropdown Styling */
    .dropdown {
        position: relative;
        display: inline-block;
    }

    .dropbtn {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 15px;
        cursor: pointer;
        font-size: 16px;
        font-weight: bold;
        border-radius: 5px;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        right: 0;
        background-color: white;
        min-width: 120px;
        box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
        z-index: 1000;
        border-radius: 5px;
    }

    .dropdown-content a {
        color: black;
        padding: 10px 15px;
        text-decoration: none;
        display: block;
        font-size: 14px;
    }

    .dropdown-content a:hover {
        background-color: #f1f1f1;
    }

    .dropdown:hover .dropdown-content {
        display: block;
    }
</style>
