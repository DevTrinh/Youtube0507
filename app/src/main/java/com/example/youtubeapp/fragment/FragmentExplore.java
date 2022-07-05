package com.example.youtubeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtubeapp.MainActivity;
import com.example.youtubeapp.R;
import com.example.youtubeapp.interfacee.InterfaceDefaultValue;

public class FragmentExplore extends Fragment implements InterfaceDefaultValue {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore,
                container, false);
        mapping(view);
        return view;
    }

    public void mapping(View view ){

    }
}
