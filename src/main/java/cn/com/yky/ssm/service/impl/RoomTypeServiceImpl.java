package cn.com.yky.ssm.service.impl;

import cn.com.yky.ssm.entity.RoomType;
import cn.com.yky.ssm.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class RoomTypeServiceImpl extends BaseServiceImpl<RoomType> implements RoomTypeService {

}
