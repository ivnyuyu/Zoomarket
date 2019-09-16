<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Корзина</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700&display=swap&subset=cyrillic-ext" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Корзина</h1>
<c:if test="${PRODUCT_CART.size()==0 || PRODUCT_CART.size()==null}">
    <p>Ваша корзина пуста, добавьте товар!</p>
</c:if>
<p><a href="${pageContext.request.contextPath}/productlist">Вернуться в раздел с товарами</a></p>
<c:if test="${PRODUCT_CART.size()!=0 && PRODUCT_CART.size()!=null}">
    <table class="cart">
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Цена, руб.</th>
            <th></th>
        </tr>
</c:if>
<c:forEach var="tempProduct" items="${PRODUCT_CART}">
    <tr>
        <td>${tempProduct.name}</td>
        <td>${tempProduct.description}</td>
        <td>${tempProduct.price}</td>
        <td>
            <form action="cart" method="POST">
                <input type="hidden" value="${tempProduct.id}" name="idProduct">
                <input type="hidden" name="typeCommand" value="deleteProduct">
                <input type="submit" value="Убрать из корзины">
            </form>
        </td>
    </tr>

</c:forEach>
<c:if test="${PRODUCT_CART.size()!=0 && PRODUCT_CART.size()!=null}">
    </table>
</c:if>
<c:if test="${PRODUCT_CART.size()!=0 && PRODUCT_CART.size()!=null}">
    <div class="form" style="border:2px solid black;">
        <form  action="cart" method="POST">
            <div style="padding:20px 30px" >
                <h4 align="center">Оформить заказ</h4>
                <input type="hidden" name="typeCommand" value="order">
                <label><span>Имя:</span></label>
                <input maxlength="50" type="text" name="name" required>
                <label><span>Email:</span></label>
                <input maxlength="255" type="text" name="mail" required>
                <label><span>Адрес:</span></label>
                <input maxlength="255" type="text" name="address" required>
                <label><span>Комментарий:</span></label>
                <textarea maxlength="255" rows="10" cols="45" name="comment"></textarea>
            </div>
            <input type="submit" value="Оформить заказ">
        </form>
    </div>
</c:if>

</body>
</html>
