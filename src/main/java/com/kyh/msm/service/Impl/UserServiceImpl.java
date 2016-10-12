package com.kyh.msm.service.Impl;

import com.kyh.msm.dao.UserMapper;
import com.kyh.msm.entity.Email;
import com.kyh.msm.entity.User;
import com.kyh.msm.service.UserService;
import com.kyh.msm.vo.UserInfoCustom;
import com.kyh.msm.vo.UserQueryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by kongyunhui on 16/9/14.
 */
@Service
public class UserServiceImpl implements UserService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

    public User getUser(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> getUserAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> getUserByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public List<UserInfoCustom> getUserInfo(UserQueryVo userQueryVo) {
        return userMapper.selectUserInfo(userQueryVo);
    }

    @Override
    public List<Email> getUserInfo2() {
        return userMapper.selectUserInfo2();
    }

    @Override
    public List<User> getUserInfo3() {
        return userMapper.selectUserInfo3();
    }

    @Override
    public List<Email> getEmailInfo() {
        return userMapper.selectEmailInfo();
    }


    @Override
    public List<User> getUsersByIds(UserQueryVo userQueryVo) {
        return userMapper.selectByIds(userQueryVo);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insertSelective(user);
    }
}
