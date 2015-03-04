function verify(){
    //解决中文乱码问题的方法1、
    //页面端发出的数据做一次encodeURI，服务器端使用new String(old.getBytes("iso8859-1"),"utf-8");

    //方法2、
    //页面端发出的数据做两次encodeURI，服务器端使用URLDecoder.decode(old,"UTF-8")

    var url = "AJAXServer?name="+encodeURI(encodeURI($("#userName").val()));

    /*$.get("AJAXServer?name="+$("#userName").val(),null,function(data){
        $("#msg").html(data);
    });*/

    url = convertURL(url);
    $.get(url,null,function(data){
       $("#msg").html(data);
    });

}
//给url加时间戳，骗过浏览器，不读取缓存
function convertURL(url){
    //获取时间戳
    var timetamp = (new Date()).valueOf();
    //将时间戳信息拼接到url上
    if(url.indexOf("?")>=0){
        url = url + "&t="+timetamp;
    }else{
        url = url + "?t="+timetamp;
    }

    //url=“AJAXServer$t=11111"
    return url;
}
