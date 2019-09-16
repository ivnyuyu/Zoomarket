<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Зоомагазин</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700&display=swap&subset=cyrillic-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Зоомагазин</h1>
    <p align="right"><a href="${pageContext.request.contextPath}/cart">Корзина</a></p>
    <div class="wrapper">
        <c:forEach var="tempProduct" items="${PRODUCT_LIST}">
            <form action="productlist" method="POST">
                <div class="wrapper-product">
                    <h3>Название: ${tempProduct.name}</h3>
                    <h3>Описание: ${tempProduct.description}</h3>
                    <h3>${tempProduct.price} Рублей</h3>
                    <img style="overflow: hidden" src="${pageContext.request.contextPath}${tempProduct.photo}" width="200" height="200">
                    <input type="hidden" value="${tempProduct.id}" name="idProduct">
                    <input type="submit" value="Добавить в корзину">
                </div>
            </form>
        </c:forEach>
    </div>
</body>
</html>
