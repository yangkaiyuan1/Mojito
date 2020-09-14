package cn.com.yky.ssm.service.impl;

import cn.com.yky.ssm.entity.User;
import cn.com.yky.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
}
