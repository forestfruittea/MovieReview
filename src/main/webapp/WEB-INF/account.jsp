<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 06.12.2024
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Account</title>
</head>
<body>
<h1>Welcome, ${user.username}!</h1>
<img class="user-avatar" src="${user.fullAvatarPath}" alt="User Avatar"  width="150" height="150" />
<p>Username: ${user.username}</p>

<h2>Future Features:</h2>
<ul>
    <li><a href="#">Update Avatar (Coming Soon)</a></li>
    <li><a href="#">My Reviews (Coming Soon)</a></li>
</ul>
</body>
</html>
