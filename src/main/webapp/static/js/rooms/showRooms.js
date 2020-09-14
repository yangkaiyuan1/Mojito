layui.use(['jquery','layer', 'table','form','upload'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , upload = layui.upload;  //文件上传组件



//房屋显示的ul容器数组
    var arrUl= $("#LAY_preview").find("ul");

    //初始化所有客房数据
    loadAllRooms();

    //初始化所有的客房类型数据
    loadAllRoomType();

    //房间号唯一性验证的变量
    var checkRoomNumIf = false; 

    //七牛云储存空间域名
    var quyName="http://qgc436geq.hn-bkt.clouddn.com/";







    //将空闲的客房的显示状态由1(显示)----->0(不显示)
    $("ul").eq(0).on("click","button",function () {
        //获取客房id
        var roomid = $(this).attr("roomid");
        layer.confirm('真的删除此客房数据吗？', function (index) {
            //执行客房显示状态的修改
            updRoomsFlag(roomid,'0');
            layer.close(index);  //关闭当前的询问框
        });
    });






    //将打扫的客房进行显示和空闲状态的修改操作
    $("ul").eq(2).on("click","button",function () {
        //取到判断进行操作的变量
        var event = $(this).val();
        //获取客房id
        var roomid = $(this).attr("roomid");
        if(event=='del'){  //执行显示状态的修改操作
            layer.confirm('真的删除此客房数据吗？', function (index) {
                //执行客房显示状态的修改
                updRoomsFlag(roomid,'0');
                layer.close(index);  //关闭当前的询问框
            });
        }else {
            layer.confirm('真的此客房改为空闲状态吗？', function (index) {
                //执行客房空闲状态的修改2（打扫）----->0（空闲）
                updRoomStatus(roomid,'0');
                layer.close(index);  //关闭当前的询问框
            });
        }
    });



    //点击添加按钮
    $("#saveRoomsUI").click(function () {
        //1.清空添加的表单
        $("form").eq(0).find("input").val("");
        //回显原有默认图片
        $('#demo1').attr('src', "/img/fm1.jpg");
        $("#roomPicId").val("fm1.jpg");
        //1.将添加界面弹出
        layer.open({
            type:1,  //弹出类型
            title:"客房添加操作界面",  //弹框标题
            area:['400px','500px'],  //弹框款高度
            anim: 2,  //弹出的动画效果
            shade:0.5,  //阴影遮罩
            content:$("#saveRoomsDiv")  //弹出的内容
        });
    });

    //进行房间号的唯一性验证
    $("#roomNum").blur(function () {
        var roomNum = $(this).val();
        if(roomNum.length>=4&&roomNum.length<=5) {
            checkRoomNum(roomNum);  //进行房间号的唯一性验证
        }

    });
//自定义验证
    form.verify({  //做表单提交时的验证
        roomNum: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value.length!=4){
                return '房间号长度为4-5位';
            }
            if(!checkRoomNumIf){  //已经进行过唯一性验证了
                return '房间号已被使用';
            }
        }
    });

    //监听提交按钮，执行添加
    form.on('submit(demo3)', function (data) {
        var saveJsonRooms = data.field;  //重新将查询条件赋值
        saveJsonRooms['flag'] = '1';
        saveJsonRooms['roomStatus'] = '0';
        saveRooms(saveJsonRooms);  //执行添加
        layer.closeAll();  //关闭所有弹框
        return false;  //阻止表单跳转提交
    });


    //普通图片上传 （异步文件上传）
    var uploadInst = upload.render({
        elem: '#test1'  //绑定上传的容器
        ,url: 'rooms/uploadRoomPic' //改成您自己的上传接口
        ,accept:"file"   //允许上传所有文件类型
        ,size:'5620'  //文件上传的容量最大值 单位：kb   5M
        /*   ,auto: false  //不自动上传，手动上传*/
        /*   ,bindAction: '#test9'  //绑定开始手动上传的按钮*/
        ,field:"myFile"  //文件域中的文件的名字
        ,data:{"path":"D:\\k0502\\u3\\img"}  //上传的目标文件夹路径
        ,before: function(obj){  //服务器端上传之前的函数回调
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){  //进行图片的页面回显
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){   //执行服务器上传后的函数回调
            //上传成功
            if(res.code > 0){
                $("#roomPicId").val(res.newFileName);  //将上传后的文件名替换掉默认的上传的文件名，一并提交做客房的添加
                layer.msg('上传成功。');
            }else { //上传失败
                layer.msg('上传失败！');
            }



        }
        ,error: function(){  //上传异常的函数回调
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });










    /***********************自定义函数****************************/
    //加载所有的客房数据
    function  loadAllRooms(){
        $.ajax({
            type:'POST',
            url:'rooms/loadAll',  //调用的是base系列的方法，只需要改mapper.xml文件
            success:function (data) {
                var roomStatus0 = "";
                var roomStatus1 = "";
                var roomStatus2 = "";
                $.each(data,function (i,item) {
                    if(item.roomStatus=='0'){
                        roomStatus0 += '<li style="background-color: #009688;">';
                        roomStatus0 += '<img class="layui-anim" src="'+quyName+item.roomPic+'" width="135px" height="135px"/>';
                        roomStatus0 += '<div class="code">';
                        roomStatus0 += '<span style="display: block;color: #0C0C0C;">'+item.roomNum+'-'+item.roomType.roomTypeName+'-'+item.roomType.roomPrice+'元/天</span>';
                        roomStatus0 += '<button type="button" value="del" roomid="'+item.id+'" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>';
                        roomStatus0 += '</div>';
                        roomStatus0 += '</li>';
                    }else if(item.roomStatus=='1'){
                        roomStatus1 += '<li style="background-color: red;">';
                        roomStatus1 += '<img class="layui-anim"  src="'+quyName+item.roomPic+'" width="135px" height="135px"/>';
                        roomStatus1 += '<div class="code">';
                        roomStatus1 += '<span style="display: block;color: #0C0C0C;">'+item.roomNum+'-'+item.roomType.roomTypeName+'-'+item.roomType.roomPrice+'元/天</span>';
                        roomStatus1 += '</div>';
                        roomStatus1 += '</li>';
                    }else {
                        roomStatus2 += '<li style="background-color: blueviolet;">';
                        roomStatus2 += '<img class="layui-anim"  src="'+quyName+item.roomPic+'" width="135px" height="135px"/>';
                        roomStatus2 += '<div class="code">';
                        roomStatus2 += '<span style="display: block;color: #0C0C0C;">'+item.roomNum+'-'+item.roomType.roomTypeName+'-'+item.roomType.roomPrice+'元/天</span>';
                        roomStatus2 += '<button type="button" value="del" roomid="'+item.id+'" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>';
                        roomStatus2 += '<button type="button" value="upd" roomid="'+item.id+'" class="layui-btn layui-btn-xs layui-btn-normal">空闲</button>';
                        roomStatus2 += '</div>';
                        roomStatus2 += '</li>';
                    }
                })
                //    roomStatus0 += '<li><button type="button" value="save" class="layui-btn layui-btn-warm layui-btn-lg"><i class="layui-icon">&#xe654;</i>添加</button></li>';
                $(arrUl[0]).html(roomStatus0);
                $(arrUl[1]).html(roomStatus1);
                $(arrUl[2]).html(roomStatus2);
                hoverOpenImg();  //加载图片放大函数
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }


    //客房显示状态的修改
    function updRoomsFlag(roomid,flag) {
        $.ajax({
            type:'POST',
            url:'rooms/updByPrimaryKeySelective',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:{"id":roomid,"flag":flag},
            success:function (data) {
                if(data=='updSuccess'){
                    loadAllRooms();  //重新加载客房数据
                    layer.msg("删除成功。。",{icon: 1,time:2000,anim: 4,shade:0.5});
                }else {
                    layer.msg("删除失败！！！",{icon: 2,time:2000,anim: 3,shade:0.5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }


    //客房空闲状态的修改2（打扫）----->0（空闲）
    function updRoomStatus(roomid,roomStatus) {
        $.ajax({
            type:'POST',
            url:'rooms/updByPrimaryKeySelective',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:{"id":roomid,"roomStatus":roomStatus},
            success:function (data) {
                if(data=='updSuccess'){
                    loadAllRooms();  //重新加载客房数据
                    layer.msg("客房状态修改成功。。",{icon: 1,time:2000,anim: 4,shade:0.5});
                }else {
                    layer.msg("客房状态修改失败！！！",{icon: 2,time:2000,anim: 3,shade:0.5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }



    //加载所有的客房类型数据
    function loadAllRoomType() {
        $.ajax({
            type:'POST',
            url:'roomType/loadAll',  //调用的是base系列的方法，只需要改mapper.xml文件
            success:function (data) {
                var roomTypeStr = '<option value="" selected>--请选则客房类型--</option>';
                $.each(data,function (i,roomType) {
                    roomTypeStr += '<option value="'+roomType.id+'">'+roomType.roomTypeName+'-'+roomType.roomPrice+'</option>';
                })
                $("#selRoomType").html(roomTypeStr);
                form.render("select");  //渲染下拉框
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }


    //执行添加
    function saveRooms(saveJsonRooms) {
        $.ajax({
            type:'POST',
            url:'rooms/saveT',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:saveJsonRooms,
            success:function (data) {
                if(data=='saveSuccess'){
                    loadAllRooms();  //重新加载客房数据
                    layer.msg("客房数据添加成功。。",{icon: 1,time:2000,anim: 4,shade:0.5});
                }else {
                    layer.msg("客房数据添加失败！！！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }

    //房间号的唯一性验证
    function checkRoomNum(roomNum) {
        $.ajax({
            type:'POST',
            url:'rooms/loadCountByPramas',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:{"roomNum":roomNum},
            success:function (data) {
                if(data>0){
                    layer.tips('此房间号已被使用','#roomNum', {tips: [2,'red'],time:2000});
                    checkRoomNumIf = false;
                }else {
                    layer.tips('此房间号可用','#roomNum', {tips: [2,'green'],time:2000});
                    checkRoomNumIf = true;
                }
            },
            error:function () {
                layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
            }
        });
    }

    /*******************************js工具******************************************/

    //图片放大镜
    function hoverOpenImg(){
        var img_show = null; // tips提示
        $('img').hover(function(){
            var img = "<img class='img_msg' src='"+$(this).attr('src')+"' style='width:580px;' />";
            img_show = layer.tips(img, this,{
                tips:[2, 'rgba(41,41,41,.5)']
                ,area: ['600px']
                ,time: -1  //永久显示
                ,anim: 3
            });
        },function(){
            layer.close(img_show);
        });
        $('img').attr('style','max-width:270px');
    }






});