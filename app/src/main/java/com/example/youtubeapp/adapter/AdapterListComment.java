package com.example.youtubeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeapp.R;
import com.example.youtubeapp.item.ItemComment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListComment extends
        RecyclerView.Adapter<AdapterListComment.ListCommentViewHolder> {

    List<ItemComment> listComment;

    public AdapterListComment(List<ItemComment> listComment) {
        this.listComment = listComment;
    }

    @NonNull
    @Override
    public ListCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment_video,parent, false);
        return new ListCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCommentViewHolder holder, int position) {
        ItemComment itemComment = listComment.get(position);
        if (itemComment == null){
            return;
        }
        Picasso.get().load(itemComment.getUrlAvtChannelComment()).into(holder.ivAvtItemComment);
        holder.tvItemNameChannel.setText(itemComment.getNameChannelComment());
        holder.tvTimeAgoComment.setText(itemComment.getTimeComment());
        holder.tvContentComment.setText(itemComment.getContentComment());
        holder.tvNumberLikeComment.setText(itemComment.getNumberLikeComment());
        holder.tvReplyComment.setText(itemComment.getReplyComment());
    }

    @Override
    public int getItemCount() {
        if (listComment != null){
            return listComment.size();
        }
        return 0;
    }

    public class ListCommentViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvtItemComment;
        private TextView tvItemNameChannel, tvTimeAgoComment,
                tvContentComment, tvNumberLikeComment, tvReplyComment;

        public ListCommentViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void mapping(View view){
            ivAvtItemComment = view.findViewById(R.id.iv_avt_user_comment);
            tvItemNameChannel = view.findViewById(R.id.tv_item_name_channel);
            tvTimeAgoComment = view.findViewById(R.id.tv_item_time_ago_comment);
            tvNumberLikeComment = view.findViewById(R.id.tv_item_number_like_comment);
            tvReplyComment = view.findViewById(R.id.tv_item_reply_comment);
            tvContentComment = view.findViewById(R.id.tv_item_content_comment);
        }
    }
}
