<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html;charset=UTF-8" language = "java" %>
<html>
<head>
    <title>Bookmarks by Viktor Bedny</title>
</head>
<body>

    <h1>List of bookmarks</h1>

    <form action="${pageContext.request.contextPath}/bookmarks" method="post">
        <label for="filter">Filter bookmarks: </label>
        <br>
        <input type="text" name="filter" id="filter" placeholder="Enter substring">
        <br>
        <input type="submit" name="filterBookmarks" value="Search">
    </form>

    <form action="${pageContext.request.contextPath}/bookmarks" method="post">
        <label for="delete">Delete bookmark: </label>
        <br>
        <input type="text" name="delete" id="delete" placeholder="Enter bookmark ID to delete">
        <br>
        <input type="submit" name="deleteBookmark" value="Delete">
    </form>



    <c:set var="bookmarksList" value="${list}"/>
    <%-- <c:forEach var="num" items="${bookmark}"> --%>
        <%-- <c:out value="${num}"/> --%>
    <%-- </c:forEach> --%>

    <ul>
        ${bookmarksList}
    </ul>

    <form action="${pageContext.request.contextPath}/bookmarks" method="post">
        <label for="title">Bookmark title: </label>
        <br>
        <input type="text" name="title" id="title"  placeholder="Enter bookmark title...">
        <br>
        <label for="content">Bookmark: </label>
        <br>
        <textarea id="content" name="content" placeholder="Enter your bookmark..." rows="5"></textarea>
        <br>
        <input type="submit" name="saveBookmark" value="Save bookmark">
    </form>

</body>
</html>