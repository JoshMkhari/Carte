package com.carte.navigator.menu.models;

import android.widget.Switch;

import com.google.android.libraries.places.api.model.Place;

public class User {

    private String email;
    private int unitOfMeasurement;
    private Place.Type userPreference;

    public User( String email, int unit, int place)
    {
        this.email = email;
        switch (place)
        {
            case 0:
                this.userPreference = Place.Type.RESTAURANT;
                break;
            case 1:
                this.userPreference = Place.Type.SUPERMARKET;
                break;
            case 2:
                this.userPreference = Place.Type.TOURIST_ATTRACTION;
                break;
            case 3:
                this.userPreference = Place.Type.FOOD;
                break;
        }
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
