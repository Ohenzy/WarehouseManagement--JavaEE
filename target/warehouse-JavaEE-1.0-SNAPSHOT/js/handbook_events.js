/**
 * Изменяет фон строки и запоминает ее id
 */
selectRow = function (c) {
    var a;
    return function (b) {
        a != b ? (b.className = c, a && (a.className = ""), a = b) : a = b.className = "";
        if(a === b)
            document.getElementById('delete').value = a.getAttribute('id');
        else
            document.getElementById('delete').value = "";

    }
}("tab_act");

function validateDelete(id){
    if(document.getElementById(id).value === ''){
        alert("Выбирете строку для удаления");
        return false;
    }
}
