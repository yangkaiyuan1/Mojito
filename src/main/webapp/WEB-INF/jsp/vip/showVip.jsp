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
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="static/lib/layui/css/layui.css">
    <style type="text/css">
        .layui-table td{
            height: 60px;
        }
    </style>
    <!--引入layui的js文件-->
    <script src="static/lib/layui/layui.js"></script>
</head>
<body>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>会员信息显示</legend>
    </fieldset>
    <div align="center">
        <!--查询的表单-->
        <form class="layui-form" action="" lay-filter="example" style="margin-top: 20px;">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">身份证号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="idcard" autocomplete="off" class="layui-input" placeholder="请输入身份证号">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">会员卡号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="vipNum" autocomplete="off" class="layui-input" placeholder="请输入会员卡号">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">会员类型</label>
                    <div class="layui-input-block">
                        <select name="vipRate">
                            <option value="" selected>--会员类型--</option>
                            <option value="0.9">普通会员</option>
                            <option value="0.8">超级会员</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button class="layui-btn" lay-submit="" lay-filter="demo1"><i class="layui-icon">&#xe615;</i>查询</button>
                    </div>
                </div>
            </div>
        </form>
        <!--订单信息数据显示的容器-->
        <table id="demo" lay-filter="test"></table>
    </div>
</div>
</body>
<!--引入layui的js文件-->
<script src="static/js/vip/showVip.js"></script>
<%--吧修改页面包含进来--%>
<jsp:include page="updVip.jsp"/>

<!--表格操作模板-->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="query"><i class="layui-icon">&#xe615;</i>查看</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="upd"><i class="layui-icon">&#xe642;</i>修改</a>
</script>
<!--会员的性别自定义模板-->
<script type="text/html" id="vipGenderTpl">
    {{#  if(d.gender == 1){ }}
    <font color="blue">男</font>
    {{#  } else { }}
    <font color="#ffc0cb">女</font>
    {{#  } }}
</script>
<!--会员的类型（折扣）自定义模板-->
<script type="text/html" id="vipRateTpl">
    {{#  if(d.vipRate == 0.8){ }}
    <font color="#b8860b">超级会员</font>
    {{#  } else { }}
    <font color="#9acd32">普通会员</font>
    {{#  } }}
</script>
</html>
</html>