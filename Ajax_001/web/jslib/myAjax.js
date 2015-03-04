var xmlhttp;
function myAjax() {
    var userName = document.getElementsByName("userName")[0].value;
    alert(userName);
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = callback;
    xmlhttp.open("POST", "MyAjaxServer", true);
    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlhttp.send("name=" + userName);
}
function callback() {
    //alert(xmlhttp.readyState);
    if (xmlhttp.readyState == 4) {
        //alert(xmlhttp.status);
        if (xmlhttp.status == 200) {
            var xmlDom = xmlhttp.responseXML;
            alert(xmlhttp.status);
            var messageNode = xmlDom.getElementsByTagName("message");
            if (messageNode.length > 0) {
                var textMsg = messageNode[0].firstChild.nodeValue;
                alert(textMsg);
                document.getElementById("msg").innerHTML = textMsg;
            } else {
                document.getElementById("msg").innerHTML = "返回文本的信息为：" + xmlhttp.responseText;
            }

        } else {
            document.getElementById("msg").innerHTML = "出错了";
        }
    }
}