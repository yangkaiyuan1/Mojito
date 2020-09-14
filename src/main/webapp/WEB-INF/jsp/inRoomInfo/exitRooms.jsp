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
<body>

<!--做客房退房的表单-->
<div style="display: none;margin-top: 20px;" id="exitInRoomInfoDiv">
    <form class="layui-form layui-form-pane" action="" lay-filter="exitInRoomInfoForm" style="margin-left: 50px;">
        <input type="hidden" name="inRoomInfo_id"/><%--入住信息id--%>
        <%--折扣信息--%>
        <input type="hidden" name="vipRate" id="vipRate"/>

        <div class="layui-form-item">
            <label class="layui-form-label">房间号：</label>
            <div class="layui-input-inline">
                <input type="text" name="roomNum" id="roomsNum" lay-verify="required" autocomplete="off" class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客人姓名：</label>
                <div class="layui-input-block">
                    <input type="text" name="customerName" autocomplete="off" class="layui-input" disabled>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">身份证号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="idcard" autocomplete="off" class="layui-input" disabled>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否会员：</label>
                <div class="layui-input-block">
                    <input type="text" name="isVip" id="isVip" autocomplete="off" class="layui-input" disabled>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">会员卡号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="vipNum" id="vipNum" autocomplete="off" class="layui-input" disabled>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">房间单价：</label>
                <div class="layui-input-block">
                    <input type="text" name="roomPrice" id="onePrice" autocomplete="off" class="layui-input" disabled>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">其它消费：</label>
                <div class="layui-input-inline">
                    <input type="text" name="number" lay-verify="otherPrice" value="0" autocomplete="off" class="layui-input" placeholder="请输入金额" id="otherPrice"step="1"  min="0" onkeyup="this.value= this.value.match(/\d+(\.\d{0,2})?/) ? this.value.match(/\d+(\.\d{0,2})?/)[0] : ''">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">入住时间：</label>
                <div class="layui-input-block">
                    <input type="text" name="createDate" id="createDate" autocomplete="off" class="layui-input" disabled>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">退房时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="endDate" id="endDate" autocomplete="off" class="layui-input" disabled>
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text" style="width: 600px;">
            <label class="layui-form-label">退房备注</label>
            <div class="layui-input-block">
                <textarea rows="2" placeholder="请输入内容" lay-verify="required" name="remark" id="remark" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <span style="margin-left: 20px;">住房天数：</span>
            <span style="font-size: 30px;color: green" id="days"></span>天
            <span style="margin-left: 160px;">消费总额：￥</span>
            <span style="font-size: 40px;color: red" id="zprice"></span>元
        </div>
        <div class="layui-form-item" style="margin-left: 70px;">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="demo3"><i class="layui-icon">&#xe605;</i>结账退房</button>
        </div>
    </form>
</div>

</form>
</div>
 </body>
</html>