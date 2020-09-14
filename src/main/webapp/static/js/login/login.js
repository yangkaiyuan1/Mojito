layui.use(['jquery','layer', 'table','form','laydate'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , laydate = layui.laydate; //引用日期模块


    var verifyCheckIf = false;  //验证码验证的判断


    form.verify({
        userName: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }
            if (value.length < 3 || value.length > 12) {
                return '用户名长度为3-12位';
            }
        }
        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        ,pwd: [/^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格']
    })



    //验证码验证 绑定失去焦点事件
    $("#yzm").blur(function () {
        var userVerifyCode = $(this).val();  //获取用户输入的验证码
        if(userVerifyCode.length==5){
            //进行服务器端的验证码验证
            verifyCheck(userVerifyCode);
        }else {
            layer.tips('验证码格式错误！','#yzm', {tips: [2,'red'],time:2000,tipsMore: true});
        }
    });


    //监听登录表单完成登录
    form.on('submit(login)', function(data){
        login(data.field);  //向服务器端发送修改异步请求
        layer.closeAll(); //验证没有问题，则关闭弹框
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //3.表单的自定义验证，验证手机号的唯一性
    form.verify({  //点击提交按钮触发此自定义验证
        verifyCheck: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!verifyCheckIf){  //判断用户有进行手机号的修改，修改进行判断，不修改就不判断
                return "验证码不正确";
            }
        }
    });


    if($("#loginUIMsg").val()=="loginUIMsg"){
        layer.msg("对不起，请先登录",{icon: 3,time:2000,anim: 6,shade:0.5})
    }







    /*********************************************自定义函数**********************************************************/
    //服务器端的验证码验证
    function verifyCheck(userVerifyCode) {
        $.ajax({
            type:'POST',
            url:'user/verifyCheck',  //调用的是base系列的方法，只需要改mapper.xml文件
            async:false,  //表示可以在ajax外部取得ajax中的数据
            data:{"userVerifyCode":userVerifyCode},
            success:function (data) {


                if(data=='success'){  //验证码验证成功
                    layer.tips('验证码验证正确。','#yzm', {tips: [2,'green'],time:2000,tipsMore: true});
                    verifyCheckIf = true;
                }else {  //验证失败
                    layer.tips('验证码验证错误！','#yzm', {tips: [2,'red'],time:2000,tipsMore: true});
                    verifyCheckIf = false;
                }
            },
            error:function () {

                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }




    //进行用户名密码的登录
    function login(jsonUser) {
        $.ajax({
            type:'POST',
            url:'user/login',
            data:jsonUser,
            success:function (data) {
                console.log(jsonUser);
                if(data=='success'){
                    layer.msg("恭喜你，登录成功",{icon: 1,time:2000,anim: 3,shade:0.5});
                    //定时器 跳转到首页中去
                    setTimeout('window.location="model/toIndex"',2000);
                }else {
                    layer.msg("很遗憾，登录失败！",{icon: 2,time:2000,anim: 4,shade:0.5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 6,time:2000,anim: 6,shade:0.5});
            }
        });
    }



});