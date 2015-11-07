package com.lokalkart.models.databasehandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lokalkart.models.entities.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sourin on 04/11/15.
 */
public class CityTableHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "LokalKartDB";

    // City table name
    private static final String TABLE_CITY = "cities";

    // City Table Columns names
    private static final String KEY_CITY_ID = "city_id";
    private static final String KEY_CITY_NAME = "city_name";


    public CityTableHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_CITY_ID + " TEXT PRIMARY KEY," + KEY_CITY_NAME + " TEXT)";
        db.execSQL(CREATE_CITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);

        // Create tables again
        onCreate(db);
    }

    // Adding new city
    public void addCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CITY_ID, city.getCityId());
        values.put(KEY_CITY_NAME, city.getCityName());

        // Inserting Row
        db.insert(TABLE_CITY, null, values);
        db.close(); // Closing database connection
    }

    // Adding new cities
    public void addCities(ArrayList<City> cities) {
        SQLiteDatabase db = this.getWritableDatabase();

        for(int index = 0; index < cities.size(); index++){
            City city = cities.get(index);
            ContentValues values = new ContentValues();
            values.put(KEY_CITY_ID, city.getCityId());
            values.put(KEY_CITY_NAME, city.getCityName());

            // Inserting Row
            db.insert(TABLE_CITY, null, values);
        }

        db.close(); // Closing database connection
    }

    // Getting single city
    public City getCityFromCityId(String cityId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CITY, new String[] { KEY_CITY_ID,
                        KEY_CITY_NAME}, KEY_CITY_ID + "=?",
                new String[] { String.valueOf(cityId) }, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            City city = new City(cursor.getString(0), cursor.getString(1));
            // return city
            return city;
        }else{
            return null;
        }
    }

    // Getting single city
    public City getCityFromCityName(String cityName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CITY, new String[] { KEY_CITY_ID,
                        KEY_CITY_NAME}, KEY_CITY_NAME + "=?",
                new String[] { String.valueOf(cityName) }, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            City city = new City(cursor.getString(0), cursor.getString(1));
            // return city
            return city;
        }else{
            return null;
        }
    }

    // Getting All Cities
    public ArrayList<City> getAllCities() {
        ArrayList<City> cityList = new ArrayList<City>();
        // Select All Query
        String selectQuery = "SELECT "+ KEY_CITY_ID +", " + KEY_CITY_NAME +" FROM " + TABLE_CITY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setCityId(cursor.getString(0));
                city.setCityName(cursor.getString(1));
                // Adding city to list
                cityList.add(city);
            } while (cursor.moveToNext());
        }

        // return city list
        return cityList;
    }

    // Getting  city count
    public int getCityCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_CITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Updating single city
    public int updateCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CITY_ID, city.getCityId());
        values.put(KEY_CITY_NAME, city.getCityName());

        // updating row
        return db.update(TABLE_CITY, values, KEY_CITY_ID + " = ?",
                new String[] { String.valueOf(city.getCityId()) });
    }

    // Deleting single city
    public void deleteCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CITY, KEY_CITY_ID + " = ?",
                new String[] { String.valueOf(city.getCityId()) });
        db.close();
    }

    // Drop table
    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
    }


}
