package com.example.youtubeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.youtubeapp.fragment.FragmentExplore;
import com.example.youtubeapp.fragment.FragmentHome;
import com.example.youtubeapp.fragment.FragmentLibrary;
import com.example.youtubeapp.fragment.FragmentNotify;
import com.example.youtubeapp.fragment.FragmentSubs;
import com.example.youtubeapp.fragment.FragmentValueSearch;
import com.example.youtubeapp.interfacee.InterfaceDefaultValue;
public class MainActivity extends AppCompatActivity implements InterfaceDefaultValue {

    private FragmentExplore fragmentExplore;
    private FragmentSubs fragmentSubs;
    private FragmentHome fragmentHome;
    private FragmentNotify fragmentNotify;
    private FragmentLibrary fragmentLibrary;
    private FragmentValueSearch fragmentValueSearch;
    private ImageView ivEndNavHome, ivEndNavExplore,
            ivEndNavSubscriptions, ivEndNavNotification,
            ivEndNavLibrary, ivSearch;
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, StartApp.class));
        mapping();

        Intent getStringSearch = getIntent();
        String valueSearch = getStringSearch.getStringExtra(VALUE_SEARCH);
        if (valueSearch != null){
            Log.d("AHIHIHIHIHIHIIHIHI", valueSearch+"");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //ADD FRAGMENT
             fragmentValueSearch = new FragmentValueSearch();
            fragmentTransaction.add(R.id.cl_contains_fragment, fragmentHome, FRAGMENT_HOME);
            fragmentTransaction.addToBackStack(FRAGMENT_HOME);
            fragmentTransaction.commit();
        }
        else{
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //ADD FRAGMENT
            fragmentHome = new FragmentHome();
            fragmentTransaction.add(R.id.cl_contains_fragment, fragmentHome, FRAGMENT_HOME);
            fragmentTransaction.addToBackStack(FRAGMENT_HOME);
            fragmentTransaction.commit();
        }

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSearch = new Intent(MainActivity.this, ActivitySearchVideo.class);
                startActivity(intentToSearch);
            }
        });
    }
    public void onClickHome(@NonNull View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (view.getId()){
            case R.id.iv_end_bar_home:
                setDisplayEndNavOff();
                ivEndNavHome.setImageResource(R.drawable.ic_home_on);

                fragmentHome = new FragmentHome();
                fragmentTransaction.add(R.id.cl_contains_fragment, fragmentHome, FRAGMENT_HOME);
                fragmentTransaction.addToBackStack(FRAGMENT_HOME);
                getSupportFragmentManager().popBackStack(FRAGMENT_HOME, 0);
                break;
            case R.id.iv_end_bar_explore:
                setDisplayEndNavOff();
                ivEndNavExplore.setImageResource(R.drawable.ic_explore_on);
                fragmentExplore = new FragmentExplore();
                fragmentTransaction.add(R.id.cl_contains_fragment, fragmentExplore, FRAGMENT_EXPLORE);
                fragmentTransaction.addToBackStack(FRAGMENT_EXPLORE);
                getSupportFragmentManager().popBackStack(FRAGMENT_EXPLORE, 0);

                break;
            case R.id.iv_end_bar_subscriptions:
                setDisplayEndNavOff();
                ivEndNavSubscriptions.setImageResource(R.drawable.ic_subscrip_on);
                fragmentSubs = new FragmentSubs();
                fragmentTransaction.add(R.id.cl_contains_fragment, fragmentSubs, FRAGMENT_SUBSCRIPTION);
                fragmentTransaction.addToBackStack(FRAGMENT_SUBSCRIPTION);
                getSupportFragmentManager().popBackStack(FRAGMENT_SUBSCRIPTION, 0);
                break;
            case R.id.iv_end_bar_notifications:
                setDisplayEndNavOff();
                ivEndNavNotification.setImageResource(R.drawable.ic_notification_on);
                fragmentNotify = new FragmentNotify();
                fragmentTransaction.add(R.id.cl_contains_fragment, fragmentNotify, FRAGMENT_NOTIFICATION);
                fragmentTransaction.addToBackStack(FRAGMENT_NOTIFICATION);
                getSupportFragmentManager().popBackStack(FRAGMENT_NOTIFICATION, 0);
                break;
            case R.id.iv_end_bar_library:
                setDisplayEndNavOff();
                ivEndNavLibrary.setImageResource(R.drawable.ic_library_on);
                fragmentLibrary = new FragmentLibrary();
                fragmentTransaction.add(R.id.cl_contains_fragment, fragmentLibrary, FRAGMENT_LIBRARY);
                fragmentTransaction.addToBackStack(FRAGMENT_LIBRARY);
                getSupportFragmentManager().popBackStack(FRAGMENT_LIBRARY, 0);
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack(FRAGMENT_HOME, 0);
            setDisplayEndNavOff();
            ivEndNavHome.setImageResource(R.drawable.ic_home_on);
        }
        else{
            super.onBackPressed();
        }
    }

    public void setDisplayEndNavOff() {
        ivEndNavExplore.setImageResource(R.drawable.ic_explore_off);
        ivEndNavHome.setImageResource(R.drawable.ic_home_off);
        ivEndNavSubscriptions.setImageResource(R.drawable.ic_subscrip_off);
        ivEndNavLibrary.setImageResource(R.drawable.ic_library_off);
        ivEndNavNotification.setImageResource(R.drawable.ic_notification_off);
    }

    @SuppressLint("WrongViewCast")
    public void mapping() {
        ivSearch = findViewById(R.id.iv_top_bar_search);
        ivEndNavExplore = findViewById(R.id.iv_end_bar_explore);
        ivEndNavHome = findViewById(R.id.iv_end_bar_home);
        ivEndNavSubscriptions = findViewById(R.id.iv_end_bar_subscriptions);
        ivEndNavLibrary = findViewById(R.id.iv_end_bar_library);
        ivEndNavNotification = findViewById(R.id.iv_end_bar_notifications);
    }

}