//用户名校验的方法
//这个方法将使用XMLHTTPRequest对象来进行ajax的一步数据交互
var xmlhttp;

function verify(){
    //1.获取dom的方式获取文本框中的值
    var userName = document.getElementById("userName").value;

    //2.创建XMLHTTPRequest对象
    //这是XMLHTTPRequest对象使用中最复杂的的一步
    //需要针对IE和其他类型的浏览器建立这个对象的不同方式写不同的代码

    if(window.XMLHttpRequest ){
        //针对FireFox Mozillar,Opera Safari,IE7,IE8
        xmlhttp = new XMLHttpRequest ();
        //针对某些特定版本的mozillar的浏览器的BUG进行修正
        if(xmlhttp.overrideMimeType){
            xmlhttp.overrideMimeType("text/xml");
        }
    }else if(window.ActiveXObject){
        //针对IE6,IE5.5 IE5
        //两个可以用于创建XMLHTTPRequest对象的控件名称，保存在一个js的数组中
        //排在前面的版本较新
        var activexName = ["MSXML2.XMLHTTP","Microsoft.XMLHTTP"];
        for (var i=0; i<activexName.length;i++){
            try{
                //取出一个控件名称进行创建，如果创建成功就终止循环
                //如果失败，抛出异常，然后可以继续循环，机组尝试创建
                xmlhttp = new ActiveXObject(activexName[i]);
                break;
            }catch (e){
            }
        }
    }
    //确认XMLHTTPRequest对象创建成功
    if(!xmlhttp){
        alert("创建失败XMLHTTPRequest!");
        return;
    }else{
        alert(xmlhttp.readyState);
    }

    //二 注册回调函数
    //注册回调函数时，只需要函数名，不需要括号
    //我们需要将函数名注册，如果加上括号，就会把函数的返回值注册上
    xmlhttp.onreadystatechange = callback;

    //三 设置连接信息
    //d第一个参数http请求方式，支持所有http的请求方式主要使用get post
    //第二个表示请求的url地址 get方式请求的参数也在url中
    //第三个表示采用异步还是同步方式交互，true表示异步

    //xmlhttp.open("GET","AJAXServer?name="+userName,true);

    //POST方式请求的代码
    xmlhttp.open("POST","AJAXServer",true);
    //设置请求头
    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    //POST发送数据
    xmlhttp.send("name="+userName);
    //四 发送数据 开始和数据端进行交互
    //同步方式下send会在服务器端数据回来后才执行完
    //异步方式下，send立即完成执行
    xmlhttp.send(null);
}
function callback(){
    alert(xmlhttp.readyState);
    //五 接受响应数据
    //判断对象状态是否交互完成
    if(xmlhttp.readyState == 4){
        //判断http的交互是否成功
        if(xmlhttp.status == 200) {
            //获取服务器端返回的数据
            //获取服务器端输出的纯文本数据
            var responseMsgText = xmlhttp.responseText;
            //将数据显示在页面上
            document.getElementById("msg").innerHTML=responseMsgText;

        } else{
            alert("出错了！");
        }
    }
}