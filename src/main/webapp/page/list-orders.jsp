<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Заказы от пользователей</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700&display=swap&subset=cyrillic-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Заказы</h1>
<p><a href="${pageContext.request.contextPath}/warehouse">Склад</a> &emsp; <a
        href="${pageContext.request.contextPath}/orders">Заказы</a> &emsp; <a
        href="${pageContext.request.contextPath}/changeproduct">Изменение товара</a></p>
<table class="cart">
    <tr>
        <th>Id заказа</th>
        <th>Имя покупателя</th>
        <th>Email покупателя</th>
        <th>Адрес покупателя</th>
        <th>Комментарий</th>
        <th>Заказ:</th>
        <th>Выполнить</th>
    </tr>
    <c:forEach var="tempOrder" items="${ORDERS}">
        <c:url var="tempLink" value="/orders">
            <c:param name="command" value="COMPLETE_ORDER"/>
            <c:param name="orderId" value="${tempOrder.id}"/>
        </c:url>
        <tr>
            <td>${tempOrder.id}</td>
            <td>${tempOrder.name}</td>
            <td>${tempOrder.mail}</td>
            <td>${tempOrder.address}</td>
            <td>${tempOrder.comment}</td>
            <td>
                <c:forEach var="tempProduct" items="${tempOrder.productList}">
                    <p>Id: ${tempProduct.id} Наименование: ${tempProduct.name}</p>
                </c:forEach>
            </td>
            <td>
                <c:if test="${tempOrder.active==true}">
                    <a href="${tempLink}">Пометить заказ как выполненный</a>
                </c:if>
                <c:if test="${tempOrder.active==false}">
                    <p>Заказ выполнен</p>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
