package com.example.helislaptop.foodsharing;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.helislaptop.foodsharing.database.AppDatabase;
import com.facebook.stetho.Stetho;

public class FoodApplication extends Application {
    public static AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);


        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "food_db").build();
    }
    public static AppDatabase getDataBase() {
        return database;
    }

}

