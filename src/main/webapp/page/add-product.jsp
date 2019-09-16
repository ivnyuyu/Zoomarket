<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Добавить товар</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700&display=swap&subset=cyrillic-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<table class="cart">
    <tr>
        <th>Название</th>
        <th>Описание</th>
        <th>Цена</th>
        <th>Фотография и ее название</th>
    </tr>
    <form action="changeproduct" method="post" enctype="multipart/form-data">
        <tr>
            <input type="hidden" name="addProduct" value="addProduct">
            <td><input maxlength="50" type="text" name="name"></td>
            <td><textarea maxlength="255" rows="10" cols="45" name="description"></textarea></td>
            <td><input type="number" min="0" name="price"></td>
            <td><input maxlength="255" type="text" name="photoName"/>
                <input type="file" name="file"/></td>
        </tr>
        <tr>
            <td colspan="4"><input type="submit" value="Добавить"></td>
        </tr>
    </form>

</table>
<c:url var="addLink" value="changeproduct">
    <c:param name="command" value="LIST"/>
</c:url>
<h3><a href="${addLink}">Вернуться назад</a></h3>
</body>
</html>
