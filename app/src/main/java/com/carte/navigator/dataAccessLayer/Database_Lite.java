package com.carte.navigator.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.carte.navigator.menu.models.Model_User;

import java.util.ArrayList;

public class Database_Lite extends SQLiteOpenHelper {
    private static final String USER_TABLE = "USER_TABLE";
    private  static final String USER_ID = "ID";
    public static final String COLUMN_USER_EMAIL = "EMAIL";
    public static final String COLUMN_USER_PASS = "PASS";
    private static final String COLUMN_PREF = "PREF";
    private static final String COLUMN_UNIT = "UNIT";
    public Database_Lite(@Nullable Context context) {
        super(context, "user.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//(freecodecamp,2020)
        //Table Creation Statements

        //Model_User Table
        String         //Model_User Table
                tableStatement = ("CREATE TABLE " + USER_TABLE +
                "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_EMAIL + " TEXT, "
                + COLUMN_PREF + " INTEGER, "
                + COLUMN_UNIT + " INTEGER, "
                + COLUMN_USER_PASS + " TEXT);");
        db.execSQL(tableStatement);

        ContentValues cv = new ContentValues();

        //Populate
        //Populate Users Table
        cv.put(COLUMN_USER_EMAIL,"DefaultUser");
        cv.put(COLUMN_PREF,2);//2 is Points of Interest/ Attractions
        cv.put(COLUMN_UNIT,0);//0 is metres
        db.insert(USER_TABLE,null,cv);
        cv.clear();
    }

    public void removeUserData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + USER_TABLE);
    }
    public String updateUserPref(Model_User model_user)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_PREF, model_user.getUserPreference());
            // db.update(USER_TABLE,cv,"ID=1",null);
            db.update(USER_TABLE,cv,COLUMN_USER_EMAIL + "=?",new String[]{model_user.getEmail()});
            cv.clear();
            return "Success";
        }catch (Exception ignored)
        {
            return "Failed";
        }
    }

    public String updateUserUnit(Model_User model_user)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_UNIT, model_user.getUnitOfMeasurement());
            // db.update(USER_TABLE,cv,"ID=1",null);
            db.update(USER_TABLE,cv,COLUMN_USER_EMAIL + "=?",new String[]{model_user.getEmail()});
            cv.clear();
            return "Success";
        }catch (Exception ignored)
        {
            return "Failed";
        }
    }

    public ArrayList<Database_Model_User> getAllUsers()//(freecodecamp,2020)
    {
        ArrayList<Database_Model_User> users = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            //loop through the cursor result set and create
            do{
                int userID = cursor.getInt(0);
                String email = cursor.getString(1);
                ;
                int pref = cursor.getInt(2);
                int unit = cursor.getInt(3);
                String pass = cursor.getString(4);

                Model_User model_user = new Model_User(email,unit,pref);
                Database_Model_User database_model_user = new Database_Model_User(model_user,pass);
                users.add(database_model_user);
            }while (cursor.moveToNext());
        }
        //failure means list is empty

        cursor.close();
        return users;
    }

    public String addUser(Model_User model_user, String pass) {//(freecodecamp,2020)

        //encrypt here
        pass = CustomHash.encode(pass);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        ArrayList<Model_User> users = new ArrayList<>();
        for (Database_Model_User database_model_user: getAllUsers()
             ) {
            users.add(database_model_user.get_modelUser());
        }
        String oldUser ="DefaultUser";

        try
        {
            if (users.size()==1) {
                cv.put(COLUMN_USER_EMAIL, model_user.getEmail());
                cv.put(COLUMN_PREF, model_user.getUserPreference());
                cv.put(COLUMN_UNIT, model_user.getUnitOfMeasurement());
                cv.put(COLUMN_USER_PASS,pass);
                db.update(USER_TABLE,cv,COLUMN_USER_EMAIL + "=?",new String[]{oldUser});
                return "true switch";
            }
            else {
                try {
                    //Model_User table
                    cv.put(COLUMN_USER_EMAIL, model_user.getEmail());
                    cv.put(COLUMN_PREF, model_user.getUserPreference());
                    cv.put(COLUMN_UNIT, model_user.getUnitOfMeasurement());
                    cv.put(COLUMN_USER_PASS,pass);
                    db.insert(USER_TABLE, null, cv);
                    cv.clear();
                    return "true";
                } catch (Exception e) {

                    return "false";
                }
            }
        }catch (Exception e)
        {
            return "false switch";
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//(freecodecamp,2020)

    }
}
