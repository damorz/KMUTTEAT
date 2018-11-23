package com.kmutteats.srinuan.firebasekmutteat;


import android.widget.ImageView;

public class Menu {
    String namemenu;
    String price;
    String descrip;
    String userId;
    String Url;
    String nameresmenu;
    ImageView picmenu;

    public Menu(String userId,String namemenu, String price, String descrip,String url,String nameresmenu) {
        setUserId(userId);
        this.namemenu = namemenu;
        this.price = price;
        this.descrip = descrip;
        this.Url = url;
        this.nameresmenu = nameresmenu;
        this.picmenu = picmenu;
    }

    public String getNameresmenu() {
        return nameresmenu;
    }

    public void setNameresmenu(String nameresmenu) {
        this.nameresmenu = nameresmenu;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public ImageView getPicmenu() {
        return picmenu;
    }

    public void setPicmenu(ImageView picmenu) {
        this.picmenu = picmenu;
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

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
