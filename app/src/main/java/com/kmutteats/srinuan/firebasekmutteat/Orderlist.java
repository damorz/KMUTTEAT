package com.kmutteats.srinuan.firebasekmutteat;

public class Orderlist {
    String emailOrder;
    String priceOrder;
    String userId;
    String UrlOrder;
    String foodnameOrder;

    public Orderlist(String userId,String foodnameOrder,String priceOrder,String emailOrder,String urlOrder)
    {
        setUserId(userId);
        this.emailOrder =emailOrder;
        this.priceOrder = priceOrder;
        this.UrlOrder = urlOrder;
        this.foodnameOrder = foodnameOrder;

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
