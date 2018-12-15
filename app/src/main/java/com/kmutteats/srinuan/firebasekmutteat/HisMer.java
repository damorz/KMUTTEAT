package com.kmutteats.srinuan.firebasekmutteat;

public class HisMer
{
    String CountHisMer;
    String priceHisMer;
    String userId;
    String UrlHisMer;
    String foodnameHisMer;
    String EmailHisMer;

    public HisMer(String userId,String foodnameHisMer,String countHisMer,String priceHisMer,String emailHisMer,String urlHisMer)
    {
        setUserId(userId);
        this.CountHisMer = countHisMer;
        this.EmailHisMer = emailHisMer;
        this.foodnameHisMer = foodnameHisMer;
        this.priceHisMer = priceHisMer;
        this.UrlHisMer = urlHisMer;
    }

    public String getCountHisMer() {
        return CountHisMer;
    }

    public void setCountHisMer(String countHisMer) {
        CountHisMer = countHisMer;
    }

    public String getPriceHisMer() {
        return priceHisMer;
    }

    public void setPriceHisMer(String priceHisMer) {
        this.priceHisMer = priceHisMer;
    }

    public String getUserId() {
        return userId;
    }

    public String getUrlHisMer() {
        return UrlHisMer;
    }

    public void setUrlHisMer(String urlHisMer) {
        UrlHisMer = urlHisMer;
    }

    public String getFoodnameHisMer() {
        return foodnameHisMer;
    }

    public void setFoodnameHisMer(String foodnameHisMer) {
        this.foodnameHisMer = foodnameHisMer;
    }

    public String getEmailHisMer() {
        return EmailHisMer;
    }

    public void setEmailHisMer(String emailHisMer) {
        EmailHisMer = emailHisMer;
    }

    private void setUserId(String userId) {
    }
}
