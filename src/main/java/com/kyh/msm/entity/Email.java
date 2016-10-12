package com.kyh.msm.entity;

import java.io.Serializable;

/**
 * Created by kongyunhui on 16/9/19.
 */
public class Email implements Serializable{
    private int id;
    private String emailName;
    private int user_id;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", emailName='" + emailName + '\'' +
                ", user_id=" + user_id +
                ", user=" + user +
                '}';
    }
}
