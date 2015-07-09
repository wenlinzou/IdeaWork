<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>显示图片</title>
</head>
<body>
<c:if test="${existPhoto==null }">
    <a id="jumppath" href="" onclick="getImgPath();"><img id="imgPath" src="<%=basePath %>resource/images/s6_fb.jpg"
                                                          alt="beauty"/></a>
</c:if>
<c:if test="${existPhoto!=null }">
    <a id="jumppath" href="" onclick="getImgPath();"><img id="imgPath" src="<%=basePath %>resource/images/s6_fbCopy.jpg"
                                                          alt="beauty"/></a>
</c:if>
<script type="text/javascript">
    function getImgPath() {
        var imgObj = document.getElementById("imgPath");
        var imgpath = imgObj.src;
        var aObj = document.getElementById("jumppath");
        aObj.href = '<%=basePath%>photoGrey.html?imgPath=' + imgpath;
    }
</script>
</body>
</html>
