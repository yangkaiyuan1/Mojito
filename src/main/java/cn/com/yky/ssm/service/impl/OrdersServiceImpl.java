package cn.com.yky.ssm.service.impl;

import cn.com.yky.ssm.entity.InRoomInfo;
import cn.com.yky.ssm.entity.Orders;
import cn.com.yky.ssm.entity.RoomSale;
import cn.com.yky.ssm.entity.Rooms;
import cn.com.yky.ssm.utils.DateUitls;
import cn.com.yky.ssm.service.OrdersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单业务层实现类
 */
@Service
@Transactional(readOnly = false)
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {
    //重写OrdersServiceImpl的添加方法，让程序在此时直接执行该子类的方法 不需要再去父类寻找公共方法

    @Override
    public String saveT(Orders orders) throws Exception{
        //完成订单数据的添加
        int insOrdersCount = baseMapper.insert(orders);
        //修改入住信息的状态，未退房->已退房 outRoomStatus：0--1
        //新建入住信息数据对象
        InRoomInfo inRoomInfo = new InRoomInfo();
        //往修改的对象中设置参数
        inRoomInfo.setId(orders.getIriId());
        inRoomInfo.setOutRoomStatus("1");
        //执行入住信息的修改
        int updINICount = inRoomInfoMapper.updateByPrimaryKeySelective(inRoomInfo);
        //修改客房状态
        //根据入住信息id查询出入住信息

        InRoomInfo selInRoomInfo = inRoomInfoMapper.selectByPrimaryKey(orders.getIriId());
        //新建客房对象
        Rooms rooms = new Rooms();
        //往要被修改的客房信息中设置值
        rooms.setId(selInRoomInfo.getRoomId());
        rooms.setRoomStatus("2");//已入住---打扫 roomstatus1---2
        int updRoomsCount = roomsMapper.updateByPrimaryKeySelective(rooms);

        if(insOrdersCount>0&&updINICount>0&&updRoomsCount>0){
            return "success";
        }else {
            return "fail";
        }

    }

    @Override
    public String afterOrdersPay(String orderNum) throws Exception {
        //1.根据订单编号查询单个订单数据
        //1.1.新建订单查询的条件对象
        Orders praOrders = new Orders();
        //1.2.将订单编号设置进去
        praOrders.setOrderNum(orderNum);
        //1.3.执行条件查询单个数据
        Orders orders = baseMapper.selOneByPramas(praOrders);
        //2.修改订单支付状态 由0（未结算）---->1（已结算）
        //2.1.新建修改的订单对象
        Orders updOrders = new Orders();
        //2.2.将订单主键id和状态设置进去
        updOrders.setId(orders.getId());
        updOrders.setOrderStatus("1");
        //2.3.执行动态修改
        int updOrdersCount = baseMapper.updateByPrimaryKeySelective(updOrders);
        //3.完成销售记录的添加
        //3.1.新建销售记录对象
        RoomSale roomSale = new RoomSale();
        //3.2.往此对象中设置数据
        String[] orderOthers = orders.getOrderOther().split(",");
        roomSale.setRoomNum(orderOthers[0]);
        roomSale.setCustomerName(orderOthers[1]);
        roomSale.setStartDate(DateUitls.strToDate(orderOthers[2]));
        roomSale.setEndDate(DateUitls.strToDate(orderOthers[3]));
        roomSale.setDays(Integer.valueOf(orderOthers[4]));
        String[] orderPrice = orders.getOrderPrice().split(",");
        //客房单价
        roomSale.setRoomPrice(Double.valueOf(orderPrice[0]));
        //其它消费
        roomSale.setOtherPrice(Double.valueOf(orderPrice[1]));
        //实际的住房金额
        roomSale.setRentPrice(Double.valueOf(orderPrice[2]));
        //订单的实际支付金额(订单的支付总金额)
        roomSale.setSalePrice(orders.getOrderMoney());
        //优惠金额（客房单价*天数-实际的住房金额）
        Double discountPrice = roomSale.getRoomPrice()*roomSale.getDays()-roomSale.getRentPrice();
        roomSale.setDiscountPrice(discountPrice);
        //3.3.执行消费记录的添加
        int insRoomSaleCount = roomSaleMapper.insert(roomSale);
        if(updOrdersCount>0&&insRoomSaleCount>0){
            return "redirect:/model/toIndex";  //重定向到首页
        }else {
            return "redirect:/model/toErrorPay";  //重定向到异常页
        }
    }

}
