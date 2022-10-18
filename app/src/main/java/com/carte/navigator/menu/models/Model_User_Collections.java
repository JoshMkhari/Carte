package com.carte.navigator.menu.models;

import com.carte.navigator.menu.trueway_directions_json.EndPoint;

import java.util.List;

public class Model_User_Collections {

    String placeShortName;
    EndPoint endPoints;

    public Model_User_Collections(String placeShortName, EndPoint endPoints) {
        this.placeShortName = placeShortName;
        this.endPoints = endPoints;
    }

    public String getPlaceShortName() {
        return placeShortName;
    }

    public void setPlaceShortName(String placeShortName) {
        this.placeShortName = placeShortName;
    }

    public EndPoint getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(EndPoint endPoints) {
        this.endPoints = endPoints;
    }
}
