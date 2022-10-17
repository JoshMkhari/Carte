package com.carte.navigator.menu.sub.account;

public class Settings {
    public String units, landmarks;

    public Settings(){}

    public Settings(String units, String landmarks)
    {
        this.units = units;
        this.landmarks = landmarks;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(String landmarks) {
        this.landmarks = landmarks;
    }
}
