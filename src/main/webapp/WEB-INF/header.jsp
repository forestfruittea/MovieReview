<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 06.12.2024
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="header">
    <div class="nav-left">
        <a href="${pageContext.request.contextPath}/movies">Movies</a>
        <a href="${pageContext.request.contextPath}/actors">Actors</a>
        <a href="${pageContext.request.contextPath}/directors">Directors</a>
        <a href="${pageContext.request.contextPath}/genres">Genres</a>
    </div>
    <div class="nav-right">
        <a href="${pageContext.request.contextPath}/account">Account</a>
    </div>
</div>
<style>
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #333;
        padding: 10px 20px;
        color: white;
    }
    .nav-left a, .nav-right a {
        margin: 0 10px;
        text-decoration: none;
        color: white;
        font-weight: bold;
    }
    .nav-left a:hover, .nav-right a:hover {
        text-decoration: underline;
    }
</style>
