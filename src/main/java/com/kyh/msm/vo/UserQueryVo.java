package com.kyh.msm.vo;

import com.kyh.msm.entity.User;

import java.util.List;

/**
 * Created by kongyunhui on 16/9/19.
 *
 * 在这里包装所有用户相关的查询条件
 */
public class UserQueryVo {
    // 用户综合查询参数
    private UserInfoCustom userInfoCustom;
    // 用户id列表查询参数
    private List<Integer> ids;
    // 可以包装其它的查询条件，用户订单、用户账号信息等等
    // ....

    // getter、setter
    public UserInfoCustom getUserInfoCustom() {
        return userInfoCustom;
    }

    public void setUserInfoCustom(UserInfoCustom userInfoCustom) {
        this.userInfoCustom = userInfoCustom;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
