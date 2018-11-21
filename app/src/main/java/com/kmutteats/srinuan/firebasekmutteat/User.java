package com.kmutteats.srinuan.firebasekmutteat;

import android.widget.ImageView;

import java.lang.ref.SoftReference;

public class User {
    String username;
    String userstatus;
    String location;
    String userId;
    String phonenum;
    String OnOff;
    String url;
    ImageView picres;


    public User(String userId,String username,String userstatus,String location,String phonenum,String url,String OnOff)
    {
        setUserId(userId);
        this.username = username;
        this.userstatus = userstatus;
        this.location = location;
        this.phonenum = phonenum;
        this.url = url;
        this.OnOff = OnOff;
        this.picres = picres;
    }


    public String getOnOff() {
        return OnOff;
    }

    public void setOnOff(String onOff) {
        OnOff = onOff;
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



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
