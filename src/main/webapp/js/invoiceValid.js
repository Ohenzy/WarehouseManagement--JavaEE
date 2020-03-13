var products = {
    nameProduct :[],
    warehouse : [],
    quantity : [],
    unit : [],
    price : [],
    typeInvoice : ""
};

var index = 0;

function addProduct() {
    let valid = true;
    for (let item of document.getElementsByClassName("product_necessarily"))
        if (item.value === "") {
            item.style.borderColor = "red";
            valid = false;
        } else
            item.style.borderColor = "#D8D8D8";

    if(document.getElementById("quantity").value <= 0 ){
        document.getElementById("quantity").style.borderColor = 'red';
        valid = false;
    }
    if (valid) {
        console.log(index++);
        products.nameProduct.push(document.getElementById("nameProduct").value);
        products.warehouse.push(document.getElementById("warehouse").value);
        products.quantity.push(document.getElementById("quantity").value);
        products.unit.push(document.getElementById("unit").value);
        products.price.push(document.getElementById("price").value);
        products.typeInvoice = document.getElementById("type_invoice").value;
        writeTable();
    }
}

function writeTable(){
    let table =
        "<h3 class='dark_text' style='text-align: left; margin-bottom: 1%;color: #343a40; padding-bottom: 2%'>Товары </h3>" +
        "<table class='table table-hover shadow'>" +
        "<thead class='thead-dark'>"+
        "<th>Наименование</th>"+
        "<th>Склад</th>"+
        "<th>Количество</th>" +
        "<th>Ед.измерения</th>" +
        "<th>Цена</th>" +
        "<th>Общая стоимость</th></thead>";
    for (let i = 0;i<products.nameProduct.length;i++){
        table = table.concat("<tr><td>");
        table = table.concat(products.nameProduct[i]);
        table = table.concat("</td>");
        table = table.concat("<td>");
        table = table.concat($("#warehouse :selected").text());
        table = table.concat("</td>");
        table = table.concat("<td>");
        table = table.concat(products.quantity[i]);
        table = table.concat("</td>");
        table = table.concat("<td>");
        table = table.concat(products.unit[i]);
        table = table.concat("</td>");
        table = table.concat("<td>");
        table = table.concat(products.price[i]);
        table = table.concat("</td>");
        table = table.concat("<td>");
        table = table.concat(products.price[i] + products.quantity[i]);
        table = table.concat("</td></tr>");
    }
    table = table.concat("</table>");
    document.getElementById("table_products").innerHTML = table;
}

function validProduct() {
    let valid = false;
    if( validFormAdd() && products.nameProduct.length > 0){
        document.getElementById("json_products").value = JSON.stringify(products);
        valid = true;
    }
    return valid;
}


function clearTableProducts(){
    products.nameProduct = [];
    products.warehouse = [];
    products.quantity = [];
    products.unit = [];
    products.price = [];
    products.typeInvoice = "";
    document.getElementById("table_products").innerHTML = "";
    document.getElementById("json_products").value = "";
}