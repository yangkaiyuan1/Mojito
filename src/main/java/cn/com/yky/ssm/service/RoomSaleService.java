package cn.com.yky.ssm.service;

import cn.com.yky.ssm.entity.RoomSale;

import java.util.Map;

public interface RoomSaleService extends BaseService<RoomSale> {

    /**
     *   加载客房销售数据
     * @return  图形加载的数据
     * @throws Exception
     */
    Map<String,Object> findRoomSale() throws Exception;
}
