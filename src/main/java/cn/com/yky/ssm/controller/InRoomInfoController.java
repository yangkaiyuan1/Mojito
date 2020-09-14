package cn.com.yky.ssm.controller;

import cn.com.yky.ssm.entity.InRoomInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 入住信息控制层
 */
@Controller
@RequestMapping("/inRoomInfo")
public class InRoomInfoController extends BaseController<InRoomInfo> {
}
