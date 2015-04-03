/**
 * Created by Pet on 2015-04-03.
 */
onload = function () {
    messageColor();
}
function $(val) {
    return document.getElementById(val);
}
function messageColor() {
    var isOK = $("ok").value;
    //大于0 成功
    if (isOK > 0) {
        $("infoP").className = "messageS";
    } else {
        $("infoP").className = "messageE";
    }
}
