<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Склад</title>
    <style>
        tr.tab_act  td{
            background: #fff8a1;
        }
        .dark_text{
            /*color: #343a40;*/
            color: #404d56;
        }
        neces{

        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/events.js" ></script>
    <script>
        function validForm() {
            let valid = true;
            let list = document.getElementsByClassName("necessarily");
            for (let item of list){
                if(item.value === "")
                    item.style.borderColor = "red";
                    valid = false;
                }
            return valid;
        }
    </script>

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
                        <a class="dropdown-item text-white bg-dark" href="#">Приход</a>
                        <a class="dropdown-item text-white bg-dark" href="#">Расход</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Пользователи</a>
                </li>
            </ul>
        </div>
    </nav>
    <div id="action_bar" style="display: block">
        <nav  class="nav nav-underline bg-light shadow"  >
            <li class="nav-item ">
                <h6><a id="button_add"  class="nav-link text-dark" onclick="swapForm()" href="#" > Добавить </a> </h6>
            </li>
            <li class="nav-item">
                <h6> <a id="button_edit" href="#" class="nav-link  disabled" > Изменить </a></h6>
            </li>
            <li class="nav-item">
                <h6> <a id="button_delete" href="#" class="nav-link disabled"  onclick="document.getElementById('form_delete').submit(validateDelete())" > Удалить </a></h6>
            </li>
        </nav>
    </div>
</div>

<div id="view_form" class="container"  style="display: none; margin-top: 5%;width: 50%"  >
    <form action="/partners" method="post" onsubmit="return validForm()">
        <div class="form-group ">
            <label  class="dark_text " style="font-size: larger">Наименование организации <span style="color: red">*</span> </label>
            <input  class="form-control necessarily" name="nameOrg" placeholder="Введите наименование организации">
        </div>
        <div class="form-group ">
            <label class="dark_text" style="font-size: larger">Директор организации <span style="color: red">*</span></label>
            <input class="form-control necessarily" name="nameDirector" placeholder="Ф.И.О.">
        </div>
        <div class="form-group ">
            <label class="dark_text" style="font-size: larger">ИНН <span style="color: red">*</span></label>
            <input class="form-control necessarily" name="inn" placeholder="Введите ИНН">
        </div>
        <div class="form-group ">
            <label class="dark_text" style="font-size: larger">ОГРН <span style="color: red">*</span></label>
            <input class="form-control necessarily" name="ogrn" placeholder="Введите ОГРН">
        </div>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label class="dark_text" style="font-size: larger">Адрес</label>
                <input class="form-control" name="address" placeholder="Введите адрес">
            </div>
            <div class="form-group col-md-4">
                <label class="dark_text" style="font-size: larger">Телефон</label>
                <input class="form-control" name="phone" placeholder="Введите номер телефона">
            </div>
            <div class="form-group col-md-4">
                <label class="dark_text" style="font-size: larger">Email</label>
                <input class="form-control" name="email" placeholder="Введите адрес электронной почты">
            </div>
        </div>
        <div class="btn-row"  >
            <div style="margin-left: auto; margin-right: auto; text-align: center">
                <button type="submit" class="btn btn-dark col-md-3" style="margin: 20px;" > Сохранить </button>
                <button type="button" class="btn btn-dark col-md-3" style="margin: 20px;" onclick="swapForm()"> Отмена </button>
            </div>
        </div>
    </form>
</div>
<div id="view_table" style="display: block" >
    <c:if test="${!partners.isEmpty()}">
        <div  class="container text-center"  style="margin-top: 3%;">
            <h2 style="text-align: left; margin-bottom: 2%;color: #343a40"> Контрагенты </h2>
            <table class="table table-hover shadow">
                <caption> записей в базе ${partners.size()}</caption>
                <thead class="thead-dark " >
                <tr >
                    <th> Наименование организвции </th>
                    <th> Директор </th>
                    <th> Адрес </th>
                    <th> Телефон </th>
                    <th> email </th>
                    <th> ИНН </th>
                    <th> ОГРН </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="partner" items="${partners}">
                    <tr onclick="selectRow(this, ${partner.getId()})" id="${partner.getId()}">
                        <td>${partner.getNameOrganisation()}</td>
                        <td>${partner.getNameDirector()}</td>
                        <td>${partner.getAddress()}</td>
                        <td>${partner.getPhone()}</td>
                        <td>${partner.getEmail()}</td>
                        <td>${partner.getINN()}</td>
                        <td>${partner.getOGRN()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <c:if test="${partners.isEmpty()}">
        <div class="container text-center" style="margin-top: 5%"  >
            <h2 style="font-family: sans-serif; color: #999999;"> Здесь ничего нет </h2>
            <img src="/images/homePage.png">
        </div>
    </c:if>
    <form hidden id="form_delete" action="${pageContext.request.contextPath}/partners" method="post" onsubmit="validateDelete('delete')">
        <input id="delete" name="delete" type="text" value="">
    </form>
</div>
</body>
</html>
