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
    <title></title>
</head>
<!--引入layui的样式文件-->
<link rel="stylesheet" href="static/lib/layui/css/layui.css">
<!--引入layui的js文件-->
<script src="static/lib/layui/layui.js"></script>
<body>

<!--高速版-->
<div id="SOHUCS" sid="test"></div>
<script charset="utf-8" type="text/javascript" src="http://cy-cdn.kuaizhan.com/upload/changyan.js" ></script>
<script type="text/javascript">
    window.changyan.api.config({
        appid: 'cyv3opibl',
        conf: 'prod_e780d4a4e9dc45c8aa9c481895353c27'
    });
</script>

</body>
</html>