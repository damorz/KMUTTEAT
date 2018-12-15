package com.kmutteats.srinuan.firebasekmutteat;

import android.widget.ImageView;


public class Cart
{
    String namemenu;
    String price;
    String userId;
    String Url;
    String nameresmenu;
    String countfood;
    ImageView picmenu;

    public Cart(String userId,String namemenu, String price,String url,String nameresmenu,String countfood)
    {
        setUserId(userId);
        this.namemenu = namemenu;
        this.price = price;
        this.Url = url;
        this.nameresmenu = nameresmenu;
        this.countfood = countfood;
    }

    public String getCountfood() {
        return countfood;
    }

    public void setCountfood(String countfood) {
        this.countfood = countfood;
    }

    public String getNamemenu() {
        return namemenu;
    }

    public void setNamemenu(String namemenu) {
        this.namemenu = namemenu;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getNameresmenu() {
        return nameresmenu;
    }

    public void setNameresmenu(String nameresmenu) {
        this.nameresmenu = nameresmenu;
    }

    public ImageView getPicmenu() {
        return picmenu;
    }

    public void setPicmenu(ImageView picmenu) {
        this.picmenu = picmenu;
    }
}
