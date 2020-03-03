<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Склад</title>
    <style>
        tr.tab_act  td{
            background: #fff8a1;
        }
    </style>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="/js/events.js" ></script>
</head>
<body class="bg-light" >
<div class="sticky-top">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark "  >
        <a class="navbar-brand" href="/"> <img src="/images/logo.png" width="30" height="30" class="d-inline-block align-top" style="margin-right: 10px">  Управление складом </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
                        Справочники
                    </a>
                    <div class="dropdown-menu bg-dark " aria-labelledby="navbarDropdown">
                        <a class="dropdown-item text-white bg-dark" href="${pageContext.request.contextPath}/partners">Контрагент</a>
                        <a class="dropdown-item text-white bg-dark" href="${pageContext.request.contextPath}/warehouse">Склад</a>
                        <a class="dropdown-item text-white bg-dark" href="${pageContext.request.contextPath}/products">Товар</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#"  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
                        Операции
                    </a>
                    <div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown" >
                        <a class="dropdown-item text-white bg-dark" href="/invoices">Товарные накладные</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Пользователи</a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<c:if test="${!products.isEmpty()}">
    <div class="container text-center" style="margin-top: 3%">
        <h2 style="text-align: left; margin-bottom: 2%;color: #343a40"> Товары </h2>
        <table class="table table-hover shadow" >
            <caption> записей в базе ${products.size()}</caption>
            <thead class="thead-dark">
            <tr>
                <th> Наименование </th>
                <th> Количество </th>
                <th> Ед. изменрения</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}">
                <tr onclick="selectRow(this)" id="${partner.getId()}">
                    <td>${product.getName()}</td>
                    <td>${product.getQuantity()}</td>
                    <td>${product.getUnit()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${products.isEmpty()}">
    <div class="container text-center" style="margin-top: 5%"  >
        <h2 style="font-family: sans-serif; color: #999999;"> Здесь ничего нет </h2>
        <img src="/images/homePage.png">
    </div>
</c:if>
</body>
</html>
