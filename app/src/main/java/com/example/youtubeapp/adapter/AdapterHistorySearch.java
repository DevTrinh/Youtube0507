package com.example.youtubeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeapp.R;
import com.example.youtubeapp.item.ItemSearch;

import java.util.ArrayList;

public class AdapterHistorySearch extends RecyclerView.Adapter<AdapterHistorySearch.SearchViewHolder> {

    private ArrayList<ItemSearch> listSearch;

    public AdapterHistorySearch(ArrayList<ItemSearch> listSearch) {
        this.listSearch = listSearch;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        ItemSearch itemSearch = listSearch.get(position);
        if (itemSearch == null){
            return;
        }
        holder.tvItemHistory.setText(itemSearch.getString());
    }

    @Override
    public int getItemCount() {
        if (listSearch == null){
            return  0;
        }
        return listSearch.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        private TextView tvItemHistory;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemHistory = itemView.findViewById(R.id.tv_history);
        }
    }
}
