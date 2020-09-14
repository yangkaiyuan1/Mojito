package cn.com.yky.ssm.service;

import cn.com.yky.ssm.entity.Orders;

/**
 * 订单业务层接口
 */
public interface OrdersService extends BaseService<Orders> {
    //重写OrdersServiceImpl的添加方法，让程序在此时直接执行该子类的方法 不需要再去父类寻找公共方法
    String saveT(Orders orders)throws Exception;


    //订单支付成功后的回调业务(1.订单状态的修改：未支付0-->已支付1；2.消费记录的生成)
    String afterOrdersPay(String orderNum) throws Exception;



}
