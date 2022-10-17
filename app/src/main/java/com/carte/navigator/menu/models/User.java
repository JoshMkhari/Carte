package com.carte.navigator.menu.models;

import com.google.android.libraries.places.api.model.Place;

public class User {

    private String email;
    private int unitOfMeasurement;
    private Place.Type userPreference;

    public User( String email, int unit, Place.Type pref)
    {
        this.email = email;
        this.userPreference = pref;
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

    public Place.Type getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(Place.Type userPreference) {
        this.userPreference = userPreference;
    }
}
