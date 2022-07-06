package com.example.youtubeapp;

import android.app.Application;

import com.example.youtubeapp.preferences.DataLocalManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
