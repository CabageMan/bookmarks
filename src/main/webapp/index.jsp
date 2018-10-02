<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmarks by Viktor Bedny</title>
</head>
<body>

    <h1>List of bookmarks</h1>


    <c:set var="bookmarksList" value="${list}"/>


    <ul>
        <li>${bookmarksList}</li>
    </ul>

    <form action="${pageContext.request.contextPath}/bookmarks" method="post">
        <label for="title">Bookmark title: </label>
        <br>
        <input type="text" name="title" id="title" placeholder="Enter bookmark title..." value="${title}">
        <br>
        <label for="content">Фамилия: </label>
        <br>
        <textarea id="content" name="content" placeholder="Enter your bookmark..." rows="5"></textarea>
        <br>
        <input type="submit" name="signup" value="Save bookmark">
    </form>

</body>
</html>