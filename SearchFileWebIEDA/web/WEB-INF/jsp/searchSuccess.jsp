<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>搜索结果</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/searchSuccess.css">
    <link rel="syylesheet" type="text/css" href="<%=basePath%>resource/css/top.css">
    <script type="text/javascript" src="<%=basePath%>resource/js/top.js"></script>
    <script type="text/javascript" src="<%=basePath%>resource/js/searchSuccess.js"></script>
</head>

<body>

<div id="convertH">

    <div class="backIndexDiv"><input type="button" value="回到首页" onclick="backIndex()" class="backIndexBtnStyle"/></div>
    搜索结果:<c:if test="${flag>0}">0</c:if><c:if test="${flag<0}">${fileLists.size() }</c:if>

    <div id="showdiv" style="display: none">
        <form action="print.html" onsubmit="return validate_form(this)" method="post">
            <!-- 打印信息存储在隐藏标签 有些问题 待定-->
            <input type="hidden" value="${fileLists }" id=""/>
            <table>
                <tr class="writeTabTitle">
                    <td colspan="3">搜索结果写入</td>
                </tr>
                <tr class="writeTabRight">
                    <td>盘符:</td>
                    <td><input type="text" name="diskname"/></td>
                    <td id="errordisk"></td>
                </tr>
                <tr class="writeTabRight">
                    <td>文件名称:</td>
                    <td><input type="text" name="filename"/></td>
                    <td id="errorfile"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="写入进本地磁盘" id="showthis"/></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>

    <div class="btnDivStyle">
        <input type="button" value="显示" onclick="showDiv()"/>
        <input type="button" value="隐藏" onclick="hiddenDiv()"/>
        <input type="button" value="隐显" onclick="changeDiv('showdiv')" class="shBtnStyle"/>

        <input type="button" value="重命名文件去除相同部分" onclick="changeDiv('showRenameDiv')" class="renameBtnStyle"/>
        <input type="checkbox" value='全选' onclick="selectAll(this)"/>全选

        <input type="button" value="全选" onclick="checkAllByBtn(1)"/>
        <input type="button" value="取消全选" onclick="checkAllByBtn(0)"/>
        <input type="button" value="反选" onclick="checkAllByBtn(2)"/>
        <input type="hidden" value="倒置" onclick="convertHtml()"/>
    </div>

    <div id="showRenameDiv" style="display: none">
        <form method="post" action="rename.html" onsubmit="return getSelectChecked()">
            <input type="hidden" id="rename" name="rename"/>
            <table>
                <tr>
                    <td>相同部分的名称为:</td>
                    <td><input type="text" name="samename" id="samename"/></td>
                </tr>
                <tr>
                    <td colspan="2" id="nochecked"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="修改" class="btnUpdate"/></td>
                </tr>
            </table>
        </form>
    </div>

    <div>
        <a href="javascript:void(0)" onclick="changeFont('max')">大字体</a>
        <a href="javascript:void(0)" onclick="changeFont('normal')">中字体</a>
        <a href="javascript:void(0)" onclick="changeFont('min')">小字体</a>
    </div>

    <div>

        <table id="mySearchInfo">
            <tr>
                <td class="mytitle">搜索结果-文件信息</td>
            </tr>

            <c:forEach items="${fileLists }" var="file" varStatus="status">
                <tr name="changeFont">
                        <%-- <c:if test="${status.index%2!=1}">class="mytrTwo"</c:if> class="mytrOne"> --%>
                    <td>
                        <c:if test="${flag>0 }">
                            <center>${file }</center>
                        </c:if>
                        <c:if test="${flag<0 }">
                            <input type="checkbox" name="filePathList" value="${file }"/>
                            <a href="linkFile.html?linkurl=${file}">
                                <c:if test="${fileLists!=null }">${file }</c:if>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
    <div class="myright"><a href="#" onclick="gotoTop();return false;" class="myright"></a></div>

</div>

</body>

</html>
