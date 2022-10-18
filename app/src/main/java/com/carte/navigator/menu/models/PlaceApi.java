package com.carte.navigator.menu.models;

import android.util.Log;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlaceApi {
    public ArrayList<String> autoComplete(String input){
        Log.d("myFilter", "AutoComplete: ");
        ArrayList<String> arrayList = new ArrayList();
        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
            sb.append("input=" + input);
            sb.append("&key=" + "AIzaSyDqNPRkQJ3qunF4Fq_C4gkxZJPb5TlBIhQ");
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection)  url.openConnection();
            Log.d("myFilter", "myConnection: " + connection);
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            int read;
            char[]buff = new char[1024];
            while ((read=inputStreamReader.read(buff))!= -1)
            {
                jsonResult.append(buff,0,read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                connection.disconnect();
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonResult.toString());
            JSONArray predictions = jsonObject.getJSONArray("predictions");
            for(int i= 0; i< predictions.length();i++ )
            {
                arrayList.add(predictions.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("myFilter", "return arrayList; " + arrayList.size());
        return arrayList;
    }
}
