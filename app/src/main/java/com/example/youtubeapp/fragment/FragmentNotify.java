package com.example.youtubeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.youtubeapp.R;
import com.example.youtubeapp.interfacee.InterfaceDefaultValue;

public class FragmentNotify extends Fragment implements InterfaceDefaultValue {

    private SwipeRefreshLayout â;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Fragment Notify", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_notify, container,false);
        return view;
    }
}
