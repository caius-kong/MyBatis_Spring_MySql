package com.kyh.msm.dao;

import com.kyh.msm.entity.Email;
import com.kyh.msm.entity.User;
import com.kyh.msm.vo.UserInfoCustom;
import com.kyh.msm.vo.UserQueryVo;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    List<User> selectByName(String name);

    List<UserInfoCustom> selectUserInfo(UserQueryVo userQueryVo);
    List<Email> selectUserInfo2();
    List<User> selectUserInfo3();
    List<Email> selectEmailInfo();

    List<User> selectByIds(UserQueryVo userQueryVo);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}