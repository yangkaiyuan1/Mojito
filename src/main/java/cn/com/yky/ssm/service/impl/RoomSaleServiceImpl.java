package cn.com.yky.ssm.service.impl;

import cn.com.yky.ssm.entity.RoomSale;
import cn.com.yky.ssm.service.RoomSaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = false)
public class RoomSaleServiceImpl extends BaseServiceImpl<RoomSale> implements RoomSaleService {
   /**
           *   加载客房销售数据
     * @return  图形加载的数据
     * @throws Exception
     */
    //数据分析客房销售记录
    @Override
    public Map<String, Object> findRoomSale() throws Exception {
        //1.进行分组查询，使用客房编号分组将其销售金额进行相加
        List<Map<String, Object>> listMap = roomSaleMapper.selPriceByRoomNum();
        //2.新建一个Map集合，装数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //3.装图像显示提示
        dataMap.put("legend","客房销售");
        //4.装横轴数据和图形数据
        //4.1新建横轴数据的List集合
        List<String> xAxis = new ArrayList<String>();
        //4.2新建图形数据的List集合
        List<Double> series = new ArrayList<Double>();
        for (Map<String, Object> map: listMap) {
            //装横轴数据
            xAxis.add(map.get("room_num").toString());
            //装图形数据
            series.add(Double.valueOf(map.get("saleprices").toString()));
        }
        //5.将设置好的数据装入到map中
        dataMap.put("xAxis",xAxis);
        dataMap.put("series",series);
        return dataMap;
    }
}
