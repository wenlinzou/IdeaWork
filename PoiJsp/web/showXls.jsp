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
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="js/showXls.js"></script>
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
            <td><input value="Reverse" id="allChecked" type="button" onclick="checkAllByBtn(2);"/></td>
        </tr>

        <c:forEach items="${xlsVO }" var="xls" varStatus="status">
            <c:if test="${xls!=null }">
                <tr>
                    <td>${xls.stuNo}</td>
                    <td>${xls.score}</td>
                    <td>${xls.name}</td>
                    <td>${xls.college}</td>
                    <td>${xls.courseName}</td>
                    <td><input type="checkbox" name="cellLists" id="cellListId${status.index}"/></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
    <input type="button" onclick="getSelectChecked()" value="上传"/>

    <%--<script type="text/javascript" >
        function getSelectChecked() {
            var selectedCells = document.getElementsByName("cellLists");
            for (var i = 0; i < selectedCells.length; i++) {
                //选中的cells
                if (selectedCells[i].checked) {
                    var inputObj = document.getElementsByName("cellLists")[i];
                    var trNode = inputObj.parentNode.parentNode;
                    var tdNodes = trNode.childNodes;
                    var tdLength = tdNodes.length;
                    tdLength-=1;
                    //最后的input的不取
                    for (var j = 0; j < tdLength; j++) {
                        if (tdNodes[j].nodeName == "#text" && !/\s/.test(tdNodes.nodeValue)) {
                            trNode.removeChild(tdNodes[j]);
                        } else {
                            alert(tdNodes[j].innerHTML);
                        }
                    }
                }
            }
        }
    </script>--%>
</center>
</body>
</html>

