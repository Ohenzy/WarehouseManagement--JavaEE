selectRow = function (c) {
    var a;
    return function (b) {
        a != b ? (b.className = c, a && (a.className = ""), a = b) : a = b.className = "";
        if(a === b){
            document.getElementById('id_row').value = a.getAttribute('id');
            document.getElementById('button_edit').className = "nav-link text-dark";
            document.getElementById('button_delete').className = "nav-link text-dark";
        }

        else{
            document.getElementById("id_row").value = "";
            document.getElementById('button_edit').className = "nav-link disabled";
            document.getElementById('button_delete').className = "nav-link disabled";
        }

    }
}("tab_act");

function validFormAdd() {
    let valid = true;
    let list = document.getElementsByClassName("necessarily");
    for (let item of list)
        if(item.value === ""){
            item.style.borderColor = "red";
            valid = false;
        }
    return valid;
}
function validateRowId(action){
    document.getElementById("action").value = action;
    if(action !== "add" && action !== "delete_all")
        if(document.getElementById("id_row").value === ''){
        alert("Выбирете запись");
        return false;
    }
    return true;
}

function swapForm(action) {
    document.getElementById("action").value = action;
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


