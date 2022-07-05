package com.example.youtubeapp.adapter;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeapp.R;
import com.example.youtubeapp.interfacee.InterfaceClickItemMainVideo;
import com.example.youtubeapp.item.ItemVideoMain;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMainVideoYoutube extends
        RecyclerView.Adapter<AdapterMainVideoYoutube.ItemVideoMainViewHolder> {

    private ArrayList<ItemVideoMain> listItemVideoMain;
    private InterfaceClickItemMainVideo interfaceClickItemMainVideo;

    public AdapterMainVideoYoutube(ArrayList<ItemVideoMain> listItemVideoMain,
                                   InterfaceClickItemMainVideo interfaceClickItemMainVideo) {
        this.listItemVideoMain = listItemVideoMain;
        this.interfaceClickItemMainVideo = interfaceClickItemMainVideo;
    }

    @NonNull
    @Override
    public ItemVideoMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_video, parent, false);
        return new ItemVideoMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVideoMainViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        ItemVideoMain itemVideoMain = listItemVideoMain.get(position);
        if (itemVideoMain == null) {
            return;
        }
        holder.tvTitleMainItem.setText(itemVideoMain.getTvTitleVideo());
        holder.tvTimeUp.setText(itemVideoMain.getTvTimeUp());
        holder.tvNameChannel.setText(itemVideoMain.getTvNameChannel());
        holder.tvViewer.setText(itemVideoMain.getTvViewCount());
//        Picasso.get().load(item.getThumbnail()).into(holder.ivThumbnail);
//        Log.d("AAAA", itemVideoMain.getUrlAvtChannel());
//        if (itemVideoMain.getUrlAvtChannel() == null){
//            Picasso.get().load(itemVideoMain.getIvVideo()).into(holder.youTubeThumbnailView);
//        }
//        else{
//            Picasso.get().load(itemVideoMain.getUrlAvtChannel().trim()).into(holder.ivAvtChannel);
//        }

        Picasso.get().load(itemVideoMain.getIvVideo()).into(holder.youTubeThumbnailView);
//        Log.d("URL: "+position, itemVideoMain.getUrlAvtChannel()+"");
        if (itemVideoMain.getUrlAvtChannel() != null){
            Picasso.get().load(itemVideoMain.getUrlAvtChannel()).into(holder.ivAvtChannel);
        }
        else {
            Picasso.get().load("https://thumbs.dreamstime.com/b/error-message-creative-design-48389035.jpg").into(holder.ivAvtChannel);
        }

        holder.youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceClickItemMainVideo.onClickItemVideoMainVideo(position);
            }
        });

        holder.tvTitleMainItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceClickItemMainVideo.onClickItemVideoMainVideo(position);
            }
        });
        holder.ivMenuVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceClickItemMainVideo.onClickMenuItemMainVideo(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listItemVideoMain == null) {
            return 0;
        }
        return listItemVideoMain.size();
    }

    public class ItemVideoMainViewHolder extends RecyclerView.ViewHolder {
        YouTubeThumbnailView youTubeThumbnailView;
        ImageView ivAvtChannel, ivMenuVertical;
        TextView tvTitleMainItem, tvNameChannel, tvViewer, tvTimeUp;

        public ItemVideoMainViewHolder(@NonNull View itemView) {
            super(itemView);
            mapping(itemView);
        }

        public void mapping(@NonNull View view) {
            ivMenuVertical = view.findViewById(R.id.iv_item_main_menu_vertical);
            youTubeThumbnailView = view.findViewById(R.id.iv_item_main_video);
            ivAvtChannel = view.findViewById(R.id.iv_item_main_avt_video);
            tvNameChannel = view.findViewById(R.id.tv_item_main_name_channel);
            tvTimeUp = view.findViewById(R.id.tv_item_main_time_up);
            tvTitleMainItem = view.findViewById(R.id.tv_item_main_title_video);
            tvViewer = view.findViewById(R.id.tv_item_main_view_count);
        }
    }
}