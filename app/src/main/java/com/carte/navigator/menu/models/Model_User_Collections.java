package com.carte.navigator.menu.models;

import com.carte.navigator.menu.trueway_directions_json.EndPoint;

import java.util.List;

public class Model_User_Collections {

    String collectionName;
    List<EndPoint> placeName;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public List<EndPoint> getPlaceName() {
        return placeName;
    }

    public void setPlaceName(List<EndPoint> placeName) {
        this.placeName = placeName;
    }
}
