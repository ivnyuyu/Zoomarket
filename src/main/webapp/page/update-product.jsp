<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.09.2019
  Time: 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Изменить описание</title>
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
            <input type="hidden" name="updateProduct" value="updateProduct">
            <input type="hidden" name="idProduct" value="${THE_PRODUCT.id}">
            <td><input maxlength="50" type="text" name="name" value="${THE_PRODUCT.name}"></td>
            <td><textarea maxlength="255" rows="10" cols="45"  name="description">${THE_PRODUCT.description}</textarea></td>
            <td><input type="number" min="0" name="price" value="${THE_PRODUCT.price}"></td>
            <td><input maxlength="255" type="text" name="photoName" placeholder="Название файла(без символов ./|\)"/>
                <input type="file" name="file"/></td>
        </tr>
        <tr>
            <td colspan="4"><input type="submit" value="Сохранить изменения"></td>
        </tr>
    </form>

</table>
</body>
</html>
