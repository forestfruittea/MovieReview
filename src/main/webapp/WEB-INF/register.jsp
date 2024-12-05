<%-- register.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>User Registration</h2>

<form action="/MovieRev-1.0-SNAPSHOT/register" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="avatarPath">Avatar Path (optional):</label>
    <input type="text" id="avatarPath" name="avatarPath"><br><br>

    <button type="submit">Register</button>
</form>

<%-- Show error message if any --%>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

</body>
</html>
