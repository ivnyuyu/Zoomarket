<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Склад товаров</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700&display=swap&subset=cyrillic-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Склад</h1>
<p><a href="${pageContext.request.contextPath}/warehouse">Склад</a> &emsp; <a
        href="${pageContext.request.contextPath}/orders">Заказы</a> &emsp; <a
        href="${pageContext.request.contextPath}/changeproduct">Изменение товара</a></p>
<table class="cart">
    <tr>
        <th>Id продукта</th>
        <th>Наименование товара</th>
        <th>Продано</th>
        <th>Доступно на складе</th>
        <th>Изменить количество</th>
    </tr>
    <c:forEach var="tempProduct" items="${PRODUCTS}">
        <tr>
            <td>${tempProduct.id}</td>
            <td>${tempProduct.name}</td>
            <td>${tempProduct.warehouse.sold}</td>
            <td>${tempProduct.warehouse.available}</td>
            <td>
                <form action="warehouse" method="GET">
                    <input type="hidden" name="command" value="changeCountProduct">
                    <input type="hidden" name="productId" value="${tempProduct.id}">
                    <p>Изменение количества товара на складе:<input type="number" min="0" name="newCountProduct"></p>
                    <p><input type="submit" value="Изменить"></p>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
