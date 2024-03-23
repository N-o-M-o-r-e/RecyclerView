package com.project.tathanhson.recyclerview;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonUtils {
    private static final String PREF_FILE = "PREF_FILE";
    private static CommonUtils instance;
    private CommonUtils(){

    }

    public static CommonUtils getInstance(){
        if (instance ==null){
            instance = new CommonUtils();
        }
        return instance;
    }

    public void savePref(String key, String value){
        SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).apply();
    }

    public String getPref(String key){
        SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }

    public void clearPref(String key){
        SharedPreferences preferences = MyApplication.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        preferences.edit().remove(key).apply();
    }


}
