package cn.com.yky.ssm.service.impl;

import cn.com.yky.ssm.entity.Rooms;
import cn.com.yky.ssm.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class RoomServiceImpl extends BaseServiceImpl<Rooms> implements RoomService {

}
