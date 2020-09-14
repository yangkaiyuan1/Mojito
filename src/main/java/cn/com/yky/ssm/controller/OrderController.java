package cn.com.yky.ssm.controller;

import cn.com.yky.ssm.entity.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController<Orders> {


    //支付成功后的操作（订单状态的修改和消费记录的生成）
    //out_trade_no回调的当初进行支付的订单编号
    @RequestMapping("/afterOrdersPay")
    public String afterOrdersPay(String out_trade_no){
        System.out.println("out_trade_no="+out_trade_no);
        try {
            return orderService.afterOrdersPay(out_trade_no);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
