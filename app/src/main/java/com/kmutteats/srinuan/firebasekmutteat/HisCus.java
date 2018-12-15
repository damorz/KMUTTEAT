package com.kmutteats.srinuan.firebasekmutteat;

public class HisCus {
    String nameresHisCus;
    String priceHisCus;
    String userId;
    String UrlHisCus;
    String foodnameHisCus;

    public HisCus(String userId,String foodnameHisCus, String nameresHisCus,String priceHisCus,String urlHisCus)
    {
        setUserId(userId);
        this.foodnameHisCus = foodnameHisCus;
        this.nameresHisCus = nameresHisCus;
        this.UrlHisCus = urlHisCus;
        this.priceHisCus = priceHisCus;
    }

    private void setUserId(String userId) {
    }

    public String getNameresHisCus() {
        return nameresHisCus;
    }

    public void setNameresHisCus(String nameresHisCus) {
        this.nameresHisCus = nameresHisCus;
    }

    public String getPriceHisCus() {
        return priceHisCus;
    }

    public void setPriceHisCus(String priceHisCus) {
        this.priceHisCus = priceHisCus;
    }

    public String getUserId() {
        return userId;
    }

    public String getUrlHisCus() {
        return UrlHisCus;
    }

    public void setUrlHisCus(String urlHisCus) {
        UrlHisCus = urlHisCus;
    }

    public String getFoodnameHisCus() {
        return foodnameHisCus;
    }

    public void setFoodnameHisCus(String foodnameHisCus) {
        this.foodnameHisCus = foodnameHisCus;
    }
}
