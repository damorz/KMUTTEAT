package com.kmutteats.srinuan.firebasekmutteat;

import android.widget.ImageView;

import java.lang.ref.SoftReference;

public class User {
    String username;
    String userstatus;
    String location;
    String userId;
    String phonenum;
    ImageView picres;

    public User(String userId,String username,String userstatus,String location,String phonenum)
    {
        setUserId(userId);
        this.username = username;
        this.userstatus = userstatus;
        this.location = location;
        this.picres = picres;
        this.phonenum = phonenum;
    }



    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }



    public ImageView getPicres() {
        return picres;
    }

    public void setPicres(ImageView picres) {
        this.picres = picres;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
