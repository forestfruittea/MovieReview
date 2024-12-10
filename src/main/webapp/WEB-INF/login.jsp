<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>

<form action="${pageContext.request.contextPath}/login" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>

<%-- Show error message if any --%>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
</body>
</html>
