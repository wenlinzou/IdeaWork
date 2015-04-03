<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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

    <title>注册页面</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="<%=basePath%>resource/js/Calendar4.js"></script>
</head>

<body>
<div>
    <center>
        <form action="<%=basePath%>register.html" method="post">

            <table border="0px">
                <tr>
                    <td>用户名</td>
                    <td>
                        <input type="text" name="username" value="${form.username }">
                        <span class="message">${form.errors.username }</span>
                    </td>
                </tr>

                <tr>
                    <td>密码</td>
                    <td>
                        <input type="password" name="password" value="${form.password }">
                        <span class="message">${form.errors.password }</span>
                    </td>
                </tr>

                <tr>
                    <td>确认密码</td>
                    <td>
                        <input type="password" name="password2" value="${form.password2 }">
                        <span class="message">${form.errors.password2 }</span>
                    </td>
                </tr>
                <tr>
                    <td>生日</td>
                    <td>
                        <input type="text" name="birthday" id="birthday" title="请选择" onclick="MyCalendar.SetDate(this)"
                               value="${form.birthday }"/>
                        <span class="message">${form.errors.birthday }</span>
                    </td>
                </tr>

                <tr>
                    <td>
                    </td>
                    <td>
                        <input type="reset" value="清空">
                        <input type="submit" value="注册">
                    </td>
                </tr>
            </table>
        </form>

    </center>
</div>
<br>
</body>
</html>
