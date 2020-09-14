package cn.com.yky.ssm.controller;

import cn.com.yky.ssm.entity.RoomSale;
import cn.com.yky.ssm.service.RoomSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/roomSale")
public class RoomSaleController extends BaseController<RoomSale>{
    @Autowired
    RoomSaleService roomSaleService;
    /**
     *   加载客房销售数据
     * @return  图形加载的数据
     * @throws Exception
     */
    @RequestMapping("/loadRoomSale")
    public @ResponseBody
    Map<String,Object> loadRoomSale(){
        try {
            return roomSaleService.findRoomSale();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





}
