package com.carte.navigator.dataAccessLayer;

import com.carte.navigator.menu.models.Model_User;

public class Database_Model_User {
    private Model_User _modelUser;
    private String _password;

    public Database_Model_User(Model_User modelUser, String password) {
        this._modelUser = modelUser;
        this._password = password;
    }

    public Model_User get_modelUser() {
        return _modelUser;
    }

    public void set_modelUser(Model_User _modelUser) {
        this._modelUser = _modelUser;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
