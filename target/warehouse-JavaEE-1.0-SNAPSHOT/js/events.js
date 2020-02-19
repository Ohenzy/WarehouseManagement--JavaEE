/**
 * Изменяет фон строки и запоминает ее id
 */
selectRow = function (c, id) {
    var a;
    return function (b) {
        a != b ? (b.className = c, a && (a.className = ""), a = b) : a = b.className = "";
        if(a === b){
            document.getElementById('delete').value = a.getAttribute('id');
            document.getElementById('button_edit').className = "nav-link text-dark";
            document.getElementById('button_delete').className = "nav-link text-dark";
        }

        else{
            document.getElementById('delete').value = "";
            document.getElementById('button_edit').className = "nav-link disabled";
            document.getElementById('button_delete').className = "nav-link disabled";
        }

    }
}("tab_act");

function validateDelete(){
    if(document.getElementById("delete").value === ''){
        alert("Выбирете строку для удаления");
        return false;
    }
}

function swapForm() {
    if(document.getElementById("view_table").style.display === "block"){
        document.getElementById("view_table").style.display = "none";
        document.getElementById("view_form").style.display = "block";
        document.getElementById("action_bar").style.display = "none";

    }
    else {
        document.getElementById("view_table").style.display = "block";
        document.getElementById("view_form").style.display = "none";
        document.getElementById("action_bar").style.display = "block";
    }
}


