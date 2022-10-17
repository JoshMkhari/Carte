package com.carte.navigator.menu.models;

import android.widget.Switch;

import com.google.android.libraries.places.api.model.Place;

public class User {

    private String email;
    private int unitOfMeasurement;
    private int userPreference;

    public User( String email, int unit, int place)
    {
        this.email = email;
        this.userPreference = place;
        this.unitOfMeasurement = unit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(int unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public int getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(int userPreference) {
        this.userPreference = userPreference;
    }
}
