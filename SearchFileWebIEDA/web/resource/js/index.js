function $(val) {
    return document.getElementById(val);
}
function trunOffPC() {
    var collRadios = document.getElementsByName("turnoff");
    var j = 0;
    for (var i = 0; i < collRadios.length; i++) {
        if (collRadios[i].checked) {
            if (confirm("确认此操作 ?")) {
                j = j + 1;
                $("turnoffID").value = collRadios[i].value;
            } else {
                return false;
            }
        }
    }
    if (j < 1) {
        $("errormsg").innerHTML = "未选中";
        $("errormsg").className = "errormsgStyle";
        return false;
    }
}

function cancelOff() {

}
//切换显示和隐藏
function changeDiv(showdiv) {
    var nodediv = document.getElementById(showdiv);
    if (nodediv.style.display == "none") {
        nodediv.style.display = "block";
    } else {
        nodediv.style.display = "none";
    }
}
function validate_disk(diskname, alerttxt, selectName) {
    with (diskname) {
        if (value == null || value == "") {
            $("errordisk").innerHTML = alerttxt;
            $("errordisk").className = selectName;
            return false;
        } else {
            return true;
        }
    }
}
function validate_form(thisform) {
    with (thisform) {
        var diskwarn = "请输入盘符名称";
        if (validate_disk(diskname, diskwarn, "errordisk") == false) {
            diskname.focus();
            return false;
        }

    }
}