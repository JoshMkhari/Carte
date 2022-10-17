package com.carte.navigator.menu.sub.account;

public class Units {
    public String metric, imperial;

    public Units(){}

    public Units(String metric, String imperial)
    {
        this.metric = metric;
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }
}
