layui.use(['jquery','layer', 'table','form','laydate'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , laydate = layui.laydate;  //引用日期模块


    var checkIdCardIf = false;  //验证手机号是否重复的变量

    var checkPhoneIf = false;  //验证手机号是否重复的变量

    //3.表单的自定义验证，验证手机号的唯一性
    form.verify({  //点击提交按钮触发此自定义验证
        checkIdcard: function(value, item){ //value：表单的值、item：表单的DOM对象
            //向服务器端发送异步请求，根据此手机号查询会员数据，判断此手机号是否重复
            checkIdcard(value);  //手机号的唯一性验证，发送ajax请求访问数据库
            //执行验证的结果
            if(!checkIdCardIf){
                return "身份证号重复";
            }
        },
        checkPhone: function(value, item){ //value：表单的值、item：表单的DOM对象
            //向服务器端发送异步请求，根据此手机号查询会员数据，判断此手机号是否重复
            checkPhone(value);  //手机号的唯一性验证，发送ajax请求访问数据库
            //执行验证的结果
            if(!checkPhoneIf){
                return "手机号重复";
            }
        }
    });

    //监听会员类型的下拉框，同时生成会员卡号
    form.on('select(vipRate)', function(data){
        var nowDate = new Date();
        //根据当前数据生成会员卡号一部分
        var vipNum = dateReplace(getNowDate(nowDate));
        if(data.value=='0.8'){ //超级会员卡号尾数为01
            vipNum += '01';
        }else {  //普通会员卡号尾数为02
            vipNum += '02';
        }
        //将会员卡号填充到输入框中
        $("#vipNum").val(vipNum);
        //将当前日期数据填充到创建日期输入框中（隐藏的）
        $("#createDate").val(getNowDate(nowDate));
    });
//监听会员添加表单提交
    form.on('submit(demo2)', function(data){
        saveVip(data.field);  //向服务器端发送添加异步请求
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    /*****************自定义数据异步交互的函数*************************/

    //根据身份证号查询会员数据条数
    function checkIdcard(value) {
        $.ajax({
            type:'POST',
            url:'vip/loadCountByPramas',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:{"idcard":value},
            async:false,  //表示可以在ajax外部取得ajax中的数据
            success:function (data) {
                if(data==1){
                    layer.tips('有重复的身份证号，验证不通过','#idcard', {tips: [2,'red'],time:2000,tipsMore: true});
                    checkIdCardIf = false;  //有重复的手机号，验证不通过
                }else {
                    layer.tips('没有重复的身份证号，验证通过','#idcard', {tips: [2,'green'],time:2000,tipsMore: true});
                    checkIdCardIf = true;  //没有重复的手机号，验证通过
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 6,time:2000,anim: 6,shade:0.5});
            }
        });
    }

    //根据手机号查询会员数据条数
    function checkPhone(value) {
        $.ajax({
            type:'POST',
            url:'vip/loadCountByPramas',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:{"phone":value},
            async:false,  //表示可以在ajax外部取得ajax中的数据
            success:function (data) {
                if(data==1){
                    layer.tips('有重复的手机号，验证不通过','#phone', {tips: [2,'red'],time:2000,tipsMore: true});
                    checkPhoneIf = false;  //有重复的手机号，验证不通过
                }else {
                    layer.tips('没有重复的手机号，验证通过','#phone', {tips: [2,'green'],time:2000,tipsMore: true});
                    checkPhoneIf = true;  //没有重复的手机号，验证通过
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 6,time:2000,anim: 6,shade:0.5});
            }
        });
    }
    function saveVip(saveJsonVip) {
        $.ajax({
            type:'POST',
            url:'vip/saveT',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:saveJsonVip,
            success:function (data) {
                if(data=='saveSuccess'){
                    layer.msg("会员数据添加成功",{icon: 1,time:2000,anim: 4,shade:0.5});
                    //定时器，2s后跳转到会员信息显示页面
                    setTimeout('window.location="model/toShowVip"',2000);
                }else {
                    layer.msg("会员数据添加失败！！",{icon: 2,time:2000,anim: 3,shade:0.5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 6,time:2000,anim: 6,shade:0.5});
            }
        });
    }


    /*********************工具函数*******************************/

    //获取当前时间字符串     Date()   ---->  yyyy/MM/dd HH:mm:ss 格式的字符串
    function getNowDate(date) {
        var sign1 = "/";
        var sign2 = ":";
        var year = date.getFullYear() // 年
        var month = date.getMonth() + 1; // 月
        var day  = date.getDate(); // 日
        var hour = date.getHours(); // 时
        var minutes = date.getMinutes(); // 分
        var seconds = date.getSeconds() //秒
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (day >= 0 && day <= 9) {
            day = "0" + day;
        }
        if (hour >= 0 && hour <= 9) {
            hour = "0" + hour;
        }
        if (minutes >= 0 && minutes <= 9) {
            minutes = "0" + minutes;
        }
        if (seconds >= 0 && seconds <= 9) {
            seconds = "0" + seconds;
        }
        var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds ;
        return currentdate;
    }

    //把 2019/01/01 12:12:12  -->  20190101121212
    function dateReplace(date) {
        date = date.replace("/","");
        date = date.replace("/","");
        date = date.replace(" ","");
        date = date.replace(":","");
        date = date.replace(":","");
        return date;
    }

});