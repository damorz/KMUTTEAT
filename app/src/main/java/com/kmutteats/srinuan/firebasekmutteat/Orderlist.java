package com.kmutteats.srinuan.firebasekmutteat;

public class Orderlist {
    String emailOrder;
    String priceOrder;
    String userId;
    String UrlOrder;
    String foodnameOrder;
    String countfood;
    String Descrip;

    public Orderlist(String userId,String foodnameOrder,String priceOrder,String emailOrder,String urlOrder,String countfood,String descrip)
    {
        setUserId(userId);
        this.emailOrder =emailOrder;
        this.priceOrder = priceOrder;
        this.UrlOrder = urlOrder;
        this.foodnameOrder = foodnameOrder;
        this.countfood =countfood;
        this.Descrip = descrip;

    }

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String descrip) {
        Descrip = descrip;
    }

    public String getCountfood() {
        return countfood;
    }

    public void setCountfood(String countfood) {
        this.countfood = countfood;
    }

    public String getEmailOrder() {
        return emailOrder;
    }

    public void setEmailOrder(String emailOrder) {
        this.emailOrder = emailOrder;
    }

    public String getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }

    public String getUserId() {
        return userId;
    }

    public String getUrlOrder() {
        return UrlOrder;
    }

    public void setUrlOrder(String urlOrder) {
        UrlOrder = urlOrder;
    }

    public String getFoodnameOrder() {
        return foodnameOrder;
    }

    public void setFoodnameOrder(String foodnameOrder) {
        this.foodnameOrder = foodnameOrder;
    }

    private void setUserId(String userId) {
    }
}
