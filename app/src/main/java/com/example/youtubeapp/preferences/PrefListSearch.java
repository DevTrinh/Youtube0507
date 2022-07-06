package com.example.youtubeapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.youtubeapp.item.ItemSearch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrefListSearch {
    private static final String LIST_KEY_SEARCH = "list_key_S";
    public static void saveArrayList(ArrayList<ItemSearch> list, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(PrefListSearch.LIST_KEY_SEARCH, json);
        editor.apply();

    }

    public static ArrayList<ItemSearch> getArrayList(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(PrefListSearch.LIST_KEY_SEARCH, null);
        Type type = new TypeToken<ArrayList<ItemSearch>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
