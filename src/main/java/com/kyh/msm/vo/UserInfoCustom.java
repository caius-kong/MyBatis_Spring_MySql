package com.kyh.msm.vo;

import com.kyh.msm.entity.User;

/**
 * Created by kongyunhui on 16/9/19.
 *
 * 用户的扩展类
 */
// 通过此类映射用户和绑定邮箱的查询结果，让此类继承包括 字段较多的pojo类
public class UserInfoCustom extends User{
    private String emailId;
    private String emailName;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    @Override
    public String toString() {
        return "UserInfoCustom{" +
                "user=" + super.toString() +
                "emailId='" + emailId + '\'' +
                ", emailName='" + emailName + '\'' +
                '}';
    }
}
