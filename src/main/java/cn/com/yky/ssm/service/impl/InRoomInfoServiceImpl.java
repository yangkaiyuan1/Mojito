package cn.com.yky.ssm.service.impl;

import cn.com.yky.ssm.entity.InRoomInfo;
import cn.com.yky.ssm.entity.Rooms;
import cn.com.yky.ssm.service.InRoomInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *   入住信息的业务层实现类
 */
@Service
@Transactional(readOnly = false)
public class InRoomInfoServiceImpl extends BaseServiceImpl<InRoomInfo> implements InRoomInfoService {
    //执行添加，1.入住信息添加  2.客房状态由0(空闲)--->1(已入住)

    //重写父类中的添加入住信息的方法

    @Override
    public String saveT(InRoomInfo inRoomInfo) throws Exception {
        //1.完成入住信息的添加

        int insINICount =baseMapper.insert(inRoomInfo);
        //2.完成客房状态的修改
        //2.1新建客房对象
        Rooms rooms = new Rooms();
        rooms.setId(inRoomInfo.getRoomId());
        rooms.setRoomStatus("1");//客房状态由0(空闲)--->1(已入住)
        //2.2执行修改客房数据
        int updRoomsCount=roomsMapper.updateByPrimaryKeySelective(rooms);

        if (insINICount>0&&updRoomsCount>0){
            return "success";
        }else{
            return "fail";
        }

    }
}
