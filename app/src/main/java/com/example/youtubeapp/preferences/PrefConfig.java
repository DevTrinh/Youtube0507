package com.example.youtubeapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.youtubeapp.item.ItemSearch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PrefConfig {
    private static final String LIST_KEY = "list_key";

    public static void writeListSearch(Context context, ArrayList<ItemSearch> arrayList){
        Gson gson = new Gson();
        String string  = gson.toJson(arrayList);
        SharedPreferences pref  = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY, string);
        editor.apply();

    }

    public static ArrayList<ItemSearch> listSearchFromPref(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string  = preferences.getString(LIST_KEY, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ItemSearch>>(){}.getType();
        ArrayList<ItemSearch> list = gson.fromJson(string, type);
        return list;
    }
}
