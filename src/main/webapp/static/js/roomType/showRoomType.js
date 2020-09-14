layui.use(['jquery','layer','form','element','laypage'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        ,element = layui.element  //引用面板模块
        ,laypage = layui.laypage  //引用分页模块
        ,form=layui.form; //
    //七牛云储存空间域名
    var quyName="http://qgc436geq.hn-bkt.clouddn.com/";




    var page = 1;  //当前页初始值为1

    var limit = 3;  //每一页数据条数

    var count = 0;  //总的数据条数

    var checkRoomsOfRoomTypeIf = false;  //验证房房型是否可删的判断

    var checkRoomTypeNameIf = false;   //验证房型名称唯一性判断


    //初始化客房类型首页数据，只执行1次
    loadPageRoomType();

    //初始化分页加载
    loadPage();


//监听折叠面板
    element.on('collapse(test)', function(data){
        if(data.show){  //面板展开时的操作
            //获取房型信息的id
            var roomTypeId = $(this).attr("roomTypeId");
            //根据此房型id数据查询多个客房数据
            loadRoomsByRoomTypeId(roomTypeId);

        }
    });




    //进行分页加载
    function loadPage(){
        //分页的完整功能
        laypage.render({
            elem: 'test1'  //绑定的分页标签容器
            ,count: count //总的数据条数
            ,page:page
            ,limit:limit //每一页显示3条数据，默认为10条
            ,limits:[2,3,5,8,10]  //进行每一页数据条数选择
            //展示分页标签的内容
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            ,jump: function(obj,first){  //进行分页操作时的函数回调
                page = obj.curr;  //当前页赋值给全局的当前页变量
                console.log("page="+page);
                //首次不执行，因为在初始化的时候就已经执行分页加载
                if(!first){
                    loadPageRoomType();  //分页数据加载
                }
            }
        });
    }


    //给页面的删除修改按钮绑定单击事件
    $("#collapseDiv").on('click','button',function () {
        //获取按钮的操作类型
        var event=$(this).attr("event");
        if (event =='del') {
            //1.获取房型id
            var id = $(this).val();
            //2.房型删除之前的验证
            checkRoomsOfRoomType(id);   //验证该房型下有没有客房数据，有则不能删除，否则可以删除
            if (checkRoomsOfRoomTypeIf) {  //可以删
                layer.confirm('真的删除此房型数据吗？', function (index) {
                    delRoomTypeById(id);  //执行房型数据删除
                    layer.close(index);  //关闭当前的询问框
                });


            } else {//有客房数据，不能删除
                layer.msg("有客房数据，不能删除！", {icon: 7, time: 2000, anim: 6, shade: 0.5})

            }
        }else {
            //执行修改操作
            //1.数据回显
            var roomTypeArr = $(this).val().split(",");
            form.val("updRoomTypeFromFilter", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "id": roomTypeArr[0]
                ,"roomTypeName": roomTypeArr[1]
                ,"roomPrice": roomTypeArr[2]
            });
            //2.弹框
            layer.open({
                type:1,  //弹出类型
                title:"房型修改操作界面",  //弹框标题
                area:['380px','280px'],  //弹框款高度
                anim: 4,  //弹出的动画效果
                shade:0.5,  //阴影遮罩
                content:$("#updRoomTypeDiv")  //弹出的内容
            });

            //执行修改操作
            //3.监听提交按钮，执行修改
            form.on('submit(demo4)', function (data) {
                var updJsonRoomType = data.field;
                updRoomType(updJsonRoomType);  //执行修改
                layer.closeAll();  //关闭所有弹框
                return false;  //阻止表单跳转提交
            });
        }
    });

    //做房型名称唯一性验证，此事件会在点击提交添加房型数据之前执行
    $("#roomTypeName").blur(function () {
        var roomTypeName = $(this).val();
        if(roomTypeName!=""){
            //验证其唯一性
            checkRoomTypeName(roomTypeName);
        }
    });

    form.verify({
        roomPrice:function (value,item) {
            if (value < 150||value>5000){
                return '房型的价格在150-5000之间'
            }
            
        }
    });
    //添加房型数据
    $("#saveRoomTypeBtn").click(function () {
        //1.清空添加表单上一次的数据
        $("#saveRoomTypeDiv form").find("input").val("");
        //2.弹框
        layer.open({
            type:1,  //弹出类型
            title:"房型添加操作界面",  //弹框标题
            area:['380px','280px'],  //弹框款高度
            anim: 3,  //弹出的动画效果
            shade:0.5,  //阴影遮罩
            content:$("#saveRoomTypeDiv")  //弹出的内容
        });
    });


    //3.监听提交按钮，执行添加
    form.on('submit(demo3)', function (data) {
        var saveJsonRoomType = data.field;
        saveRoomType(saveJsonRoomType);  //执行添加
        layer.closeAll();  //关闭所有弹框
        return false;  //阻止表单跳转提交
    });






    /*************************加载数据自定义函数*******************************/

    //分页加载客房类型数据
    function loadPageRoomType() {
        console.log("执行加载");
        $.ajax({
            type: 'POST',
            url: 'roomType/loadPageTByPramas',  //调用的是base系列的方法，只需要改mapper.xml文件
            data:{"page":page,"limit":limit},
            async:false,
            success: function (data) {  //data为服务器端的map集合数据
                console.log(data)
                count = data.count;  //将数据总的条数赋值给全局变量
                var roomTypeStr = '';
                $.each(data.data,function (i,roomType) {
                    roomTypeStr += '<div class="layui-colla-item" style="margin-top: 10px;">';
                    roomTypeStr += '<button type="button" class="layui-btn layui-btn-sm layui-btn-danger" event="del" value="'+roomType.id+'" style="float: right;">删除</button>';
                    roomTypeStr += '<button type="button" class="layui-btn layui-btn-sm layui-btn-warm" event="upd" value="'+roomType.id+','+roomType.roomTypeName+','+roomType.roomPrice+'" style="float: right;">修改</button>';
                    roomTypeStr += '<h2 class="layui-colla-title" roomTypeId="'+roomType.id+'">'+roomType.roomTypeName+'--'+roomType.roomPrice+'元/天'+'</h2>';
                    roomTypeStr += '<div class="layui-colla-content">';
                    roomTypeStr += '<p id="p'+roomType.id+'"></p>';
                    roomTypeStr += '</div>';
                    roomTypeStr += '</div>';
                })
                $("#collapseDiv").html(roomTypeStr);
                //将面板渲染
                element.render('collapse');
            },
            error: function () {
                layer.msg("服务器异常！！！", {icon: 6, time: 2000, anim: 6, shade: 0.5});
            }
        });
    }


    //验证该房型下有没有客房数据，有则不能删除，否则可以删除
    function checkRoomsOfRoomType(id){
        $.ajax({
            type:"post",
            url:"rooms/loadCountByPramas",
            async: false,
            data:{"roomTypeId":id},
            success:function (data) {
                console.log(data);
                if(data>0){
                    checkRoomsOfRoomTypeIf = false;
                }else {
                    checkRoomsOfRoomTypeIf = true;
                }
            },
            error:function (data) {
                layer.msg("服务器异常",{icon: 3,time: 2000,anim:4,shade:0.5})
            }
        });
    }

    //执行房型数据删除
    function delRoomTypeById(id){
        $.ajax({
            type:"post",
            url:"roomType/delByPrimaryKey",
            data:{"id":id},
            success:function (data) {
                if(data=='success'){
                    loadPageRoomType();  //重新加载当前页
                    loadPage();  //重新加载分页（关键就是加载总的数据条数），因为此时的数据总的条数会变化
                    layer.msg("房型数据删除成功。",{icon: 1,time: 2000,anim:4,shade:0.5})
                }else {
                    layer.msg("房型数据删除失败！",{icon: 2,time: 2000,anim:2,shade:0.5})
                }
            },
            error:function (data) {
                layer.msg("服务器异常",{icon: 3,time: 2000,anim:6,shade:0.5})
            }
        });
    }

    //修改
    function  updRoomType(updJsonRoomType) {
        $.ajax({
            type:"post",
            url:"roomType/updByPrimaryKeySelective",
            data:updJsonRoomType,
            success:function (data) {
                if(data=='updSuccess'){
                    loadPageRoomType();  //重新加载当前页
                    layer.msg("房型数据修改成功。",{icon: 1,time: 2000,anim:4,shade:0.5})
                }else {
                    layer.msg("房型数据修改失败！",{icon: 2,time: 2000,anim:2,shade:0.5})
                }
            },
            error:function (data) {
                layer.msg("服务器异常",{icon: 3,time: 2000,anim:6,shade:0.5})
            }
        });
    }

    //验证其唯一性
    function checkRoomTypeName(roomTypeName){
        $.ajax({
            type:"post",
            url:"roomType/loadCountByPramas",
            async: false,
            data:{"roomTypeName":roomTypeName},
            success:function (data) {
                if(data>0){
                    layer.tips('此房型名称已被使用','#roomTypeName', {tips: [2,'red'],time:2000});
                    checkRoomTypeNameIf = false;
                }else {
                    layer.tips('此房型名称可用','#roomTypeName', {tips: [2,'green'],time:2000});
                    checkRoomTypeNameIf = true;
                }
            },
            error:function (data) {
                layer.msg("服务器异常",{icon: 3,time: 2000,anim:4,shade:0.5})
            }
        });
    }

    //添加
    function saveRoomType(saveJsonRoomType) {
        $.ajax({
            type:"post",
            url:"roomType/saveT",
            data:saveJsonRoomType,
            success:function (data) {
                if(data=='saveSuccess'){
                    page = 1;  //将全局当前页为1
                    loadPageRoomType();  //重新加载第1页
                    loadPage();  //由于数据的条数发送变化，作用重新加载layui的分页组件
                    layer.msg("房型数据添加成功。",{icon: 1,time: 2000,anim:4,shade:0.5})
                }else {
                    layer.msg("房型数据添加失败！",{icon: 2,time: 2000,anim:2,shade:0.5})
                }
            },
            error:function (data) {
                layer.msg("服务器异常",{icon: 3,time: 2000,anim:6,shade:0.5})
            }
        });
    }


    //根据此房型id数据查询多个客房数据
    function loadRoomsByRoomTypeId(roomTypeId){
        $.ajax({
            type:"post",
            url:"rooms/loadManyByPramas",
            data:{"roomTypeId":roomTypeId},
            success:function (data) {
                if(data!=''){  //此房型有客房数据
                    var roomStatus = '<ul class="site-doc-icon site-doc-anim">';
                    $.each(data,function (i,item) {
                        if(item.roomStatus=='0'){
                            roomStatus += '<li style="background-color: #009688;">';
                        }else if(item.roomStatus=='1'){
                            roomStatus += '<li style="background-color: red;">';
                        }else {
                            roomStatus += '<li style="background-color: blueviolet;">';
                        }
                        roomStatus += '<img class="layui-anim" id="demo1" src="'+quyName+item.roomPic+'" width="135px" height="135px"/>';
                        roomStatus += '<div class="code">';
                        roomStatus += '<span style="display: block;color: #0C0C0C;">'+item.roomNum+'-'+item.roomType.roomTypeName+'-'+item.roomType.roomPrice+'元/天</span>';
                        roomStatus += '</div>';
                        roomStatus += '</li>';
                    });
                    roomStatus += '</ul>';
                    $("#p"+roomTypeId).html(roomStatus)
                }else {  //此房型没有客房数据
                    layer.msg("此房型没有客房数据！",{icon: 7,time: 2000,anim:6,shade:0.5})
                }
            },
            error:function (data) {
                layer.msg("服务器异常",{icon: 3,time: 2000,anim:6,shade:0.5})
            }
        });
    }



});

