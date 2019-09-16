<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.09.2019
  Time: 2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Изменение товара</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700&display=swap&subset=cyrillic-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Изменение товара</h1>
<c:url var="addLink" value="changeproduct">
    <c:param name="command" value="ADD"/>
</c:url>
<h3><a href="${addLink}">Добавить продукт</a></h3>
<p><a href="${pageContext.request.contextPath}/warehouse">Склад</a> &emsp; <a
        href="${pageContext.request.contextPath}/orders">Заказы</a> &emsp; <a
        href="${pageContext.request.contextPath}/changeproduct">Изменение товара</a></p>
<table class="cart">
    <tr>
        <th>Id товара</th>
        <th>Наименование</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>
    <c:forEach var="tempProduct" items="${PRODUCTS}">
        <c:url var="updateLink" value="changeproduct">
            <c:param name="command" value="UPDATE"/>
            <c:param name="productId" value="${tempProduct.id}"/>
        </c:url>

        <c:url var="deleteLink" value="changeproduct">
            <c:param name="command" value="DELETE"/>
            <c:param name="productId" value="${tempProduct.id}"/>
        </c:url>
        <tr>
            <td>${tempProduct.id}</td>
            <td>${tempProduct.name}</td>
            <td><a href="${updateLink}">Обновить</a></td>
            <td><a href="${deleteLink}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
