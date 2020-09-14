package cn.com.yky.ssm.mapper;

import cn.com.yky.ssm.entity.RoomSale;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RoomSaleMapper  extends BaseMapper<RoomSale>{
    //查询房间的销售金额,返回List
    @Select("SELECT room_num,SUM(sale_price) as saleprices from roomsale GROUP BY room_num")
    List<Map<String,Object>> selPriceByRoomNum() throws Exception;


}