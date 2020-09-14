package cn.com.yky.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/model")
public class ModelController {
    //去平台首页
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";

    }
    //去入住信息查询页面
@RequestMapping("/toShowInRoomInfo")
    public String toShowInRoomInfo(){
        return "inRoomInfo/showInRoomInfo";
    }

    //去入住信息添加界面
    @RequestMapping("/toSaveInRoomInfo")
    public String toSaveInRoomInfo(){
        return "inRoomInfo/saveInRoomInfo";
    }

    //去订单查询界面
    @RequestMapping("/toShowOrders")
public String toShowOrders(){
        return "orders/showOrders";
    }

   //去到支付的页面
    @RequestMapping("/toOrdersPay")
    public String toOrdersPay(){
        return "alipay/ordersPay";
    }

    //支付失败去到失败提示页面
    @RequestMapping("/toErrorPay")
    public String toErrorPay(){
        return "errorPay";
    }
    @RequestMapping("/toShowRoomSale")
    public String toShowRoomSale(){
        return "roomSale/showRoomSale";
    }
    @RequestMapping("/toShowVip")
    public String toShowVip(){
        return "vip/showVip";
    }


    @RequestMapping("/toSaveVip")
    public String toSaveVip(){
        return "vip/saveVip";
    }
    @RequestMapping("/toShowRooms")
    public String toShowRooms(){
        return "rooms/showRooms";
    }

    @RequestMapping("/toShowRoomType")
    public String toShowRoomType(){
        return "roomType/showRoomType";
    }

    @RequestMapping("/loginUI")
    public String loginUI(){
        return "login/login";
    }

    //去到数据分析页面

    @RequestMapping("/toShowDbi")
    public String toShowDbi(){
        return "dbi/showDbi";
    }
    @RequestMapping("/toPingLun")
    public String toPingLun(){
        return "pinglun/pinglun";
    }




}
