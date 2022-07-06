package com.example.youtubeapp.preferences;

import android.content.Context;

import com.example.youtubeapp.item.ItemSearch;

public class DataLocalManager {
    private static DataLocalManager instance;
    private MyPreferences myPreferences;
    public static void init(Context context){
        instance = new DataLocalManager();
        instance.myPreferences = new MyPreferences(context);
    }
    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }
}


