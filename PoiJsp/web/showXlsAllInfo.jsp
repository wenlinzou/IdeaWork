<%--
  Created by IntelliJ IDEA.
  User: Pet
  Date: 2015-07-06
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>

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
    <title>${titleinfo}</title>
</head>
<body>
<center>
    <div>
        <table border="1px;">
            <c:forEach items="${lists}" var="list">
                ${list}
            </c:forEach>
        </table>
    </div>
</center>
</body>
</html>
