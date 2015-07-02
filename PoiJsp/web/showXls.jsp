<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>显示Excel</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<center>
    <table style="" border="1px">
        <tr style="text-align: center;font-weight: bold;">
            <c:forEach items="${titles}" var="mytitle" varStatus="status">
                <c:if test="${mytitle!=null }">
                    <td>${mytitle}</td>
                </c:if>
            </c:forEach>
            <td>操作</td>
        </tr>

        <c:forEach items="${xlsVO }" var="xls" varStatus="status">
            <c:if test="${xls!=null }">
                <tr>
                    <td>${xls.stuNo}</td>
                    <td>${xls.score}</td>
                    <td>${xls.name}</td>
                    <td>${xls.college}</td>
                    <td>${xls.courseName}</td>
                    <td><input type="checkbox"/>上传</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
    <input type="submit" value="提交"/>
</center>
</body>
</html>

