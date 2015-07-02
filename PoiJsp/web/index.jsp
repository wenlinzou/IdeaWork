<%--
  Created by IntelliJ IDEA.
  User: Pet
  Date: 2015-07-01
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <script type="text/javascript">

        function showFilePath() {
            var thisfilepath = $("#uploadfile").val();
            alert(thisfilepath);
            document.getElementById("filepath").innerHTML = thisfilepath;
        }
    </script>
</head>
<body>
<form action="showxls.html" method="post">
    <%--<input type="file" value="上传" id="uploadfile"/>--%>
    <%--<input type="button" value="showpath" onclick="showFilePath();">--%>
    <input type="submit" value="去显示jsp"/>
</form>
<%--<a href ="showxls.html">显示这个xls</a>--%>
<span id="filepath"></span>


<div>


    <form id="form1" enctype="multipart/form-data" method="post" action="Upload.aspx">
        <div class="row">
            <label for="fileToUpload">Select a File to Upload</label><br/>
            <input type="file" name="fileToUpload" id="fileToUpload" onchange="fileSelected();"/>
        </div>
        <div id="fileName"></div>
        <div id="fileSize"></div>
        <div id="fileType"></div>
        <div class="row">
            <input type="button" onclick="uploadFile()" value="Upload"/>
        </div>
        <div id="progressNumber"></div>
    </form>
</div>
</body>
</html>
