package com.lokalkart.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.lokalkart.utils.Configurations;
import com.lokalkart.utils.GlobalConstants;

import java.security.PublicKey;

/**
 * Created by sourin on 04/11/15.
 */
public class SharedPreferenceManager {

    public static String KEY_ACCESS_TOKEN = "ACCESS_TOKEN";
    public static String KEY_REFRESH_TOKEN = "REFRESH_TOKEN";
    public static String KEY_CITY = "CITY";
    public static String KEY_CITY_TOKEN = "CITY_TOKEN";
    public static String KEY_LOCALITY = "LOCALITY";
    public static String KEY_LOCALITY_TOKEN = "LOCALITY_TOKEN";


    public static SharedPreferences.Editor getSharedPreferenceEditor(Context context){
        SharedPreferences mSharedPreferences = context.getSharedPreferences(GlobalConstants.nameSharedPreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        return editor;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(GlobalConstants.nameSharedPreference, Context.MODE_PRIVATE);
    }

    public static void saveSharedPreferences(SharedPreferences.Editor editor){
        editor.apply();
    }

    public static void saveSharedPreferencesInMainThread(SharedPreferences.Editor editor){
        editor.commit();
    }

    // ================ Operations

    public static String getValueFromSharedPreference(SharedPreferences mSharedPreferences, String key){
        return mSharedPreferences.getString(key, null);
    }

    public static void setValueInSharedPreference(SharedPreferences.Editor editor, String key, String newValue){
        editor.putString(key, newValue);
    }

    public static void deleteValueFromSharedPreference(SharedPreferences.Editor editor, String key){
        editor.remove(key);
    }

    public static void clearAllValuesFromSharedPreference(SharedPreferences.Editor editor){
        editor.clear();
    }

}
