package com.kyh.msm.service;

import com.kyh.msm.entity.Email;
import com.kyh.msm.entity.User;
import com.kyh.msm.vo.UserInfoCustom;
import com.kyh.msm.vo.UserQueryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongyunhui on 16/9/14.
 */
public interface UserService {
    public User getUser(int id);
    public List<User> getUserAll();

    List<User> getUserByName(String name);

    List<User> getUsersByIds(UserQueryVo userQueryVo);

    List<UserInfoCustom> getUserInfo(UserQueryVo userQueryVo);
    List<Email> getUserInfo2();
    List<User> getUserInfo3();
    List<Email> getEmailInfo();

    int saveUser(User user);
}
