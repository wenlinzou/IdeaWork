function verify(){
    //var userName = $("#userName").val();
    //$.get("AJAXServer?name="+userName);
    //1获取文本框中的内容//document.getElementById("id");
    //JQuery的查找节点的方式，参数中#加上属性值可以找到一个节点
    //JQuery的方法返回的都是JQuery对象，可以继续在上面执行其他的jquery方法
    var jqueryObj = $("#userName");
    //获取节点的值
    var userName = jqueryObj.val();
    //alert(userName);

    //2将文本框中的数据发送给服务器的servlet
    //使用JQuery的XMLHTTPRequest对象的封装
    $.get("AJAXServer?name="+userName,null,callback);


}
//回调函数
function callback(data){
    //alert("回调函数");
    //3接受服务器端返回的数据
    //alert(data);

    //4将服务器端返回的数据动态的显示在页面上
    //找到保存结果信息的节点
    var msgObj = $("#msg");
    //动态的改变页面中div节点中的内容
    msgObj.html(data);

    alert("");
}