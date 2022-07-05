package com.example.youtubeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeapp.R;

import java.util.ArrayList;

public class AdapterListHotKeys extends
        RecyclerView.Adapter<AdapterListHotKeys.ItemHotKeyViewHolder> {

    ArrayList<String> listKey;

    public AdapterListHotKeys(ArrayList<String> listKey) {
        this.listKey = listKey;
    }

    @NonNull
    @Override
    public ItemHotKeyViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hot_keywords,
                        parent, false);
        return new ItemHotKeyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHotKeyViewHolder holder, int position) {
        String value = listKey.get(position);
        holder.tvKeyWords.setText(value + "");
    }

    @Override
    public int getItemCount() {
        if (listKey != null) {
            return listKey.size();
        }
        return 0;
    }

    public class ItemHotKeyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvKeyWords;

        public ItemHotKeyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKeyWords = itemView.findViewById(R.id.tv_key_words);
        }
    }
}