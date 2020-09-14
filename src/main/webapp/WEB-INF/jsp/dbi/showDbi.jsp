<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <!--引入的js文件-->
    <script src="static/lib/echarts/echarts.min.js"></script>
    <script src="static/lib/echarts/jquery.min.js"></script>

</head>
<body>

<!--数据显示的容器 -->
<div align="center" id="main" style="width: 1000px;height:600px;"></div>
</body>

<script src="static/js/dbi/showDbi.js"></script>
</html>