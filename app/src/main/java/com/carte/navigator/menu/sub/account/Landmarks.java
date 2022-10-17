package com.carte.navigator.menu.sub.account;

public class Landmarks {
    public String historical, modern, popular;

    public Landmarks(){}

    public Landmarks(String historical, String modern, String popular)
    {
        this.historical = historical;
        this.modern = modern;
        this.popular = popular;
    }

    public String getHistorical() {
        return historical;
    }

    public void setHistorical(String historical) {
        this.historical = historical;
    }

    public String getModern() {
        return modern;
    }

    public void setModern(String modern) {
        this.modern = modern;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }
}
