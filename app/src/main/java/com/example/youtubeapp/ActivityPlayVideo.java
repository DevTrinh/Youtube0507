package com.example.youtubeapp;

import static com.example.youtubeapp.R.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeapp.adapter.AdapterMainVideoYoutube;
import com.example.youtubeapp.fragment.FragmentHome;
import com.example.youtubeapp.interfacee.InterfaceClickItemMainVideo;
import com.example.youtubeapp.interfacee.InterfaceDefaultValue;
import com.example.youtubeapp.item.ItemVideoMain;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ActivityPlayVideo extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener, InterfaceDefaultValue {

    YouTubePlayerView ypViewPlay;
    TextView tvTitleVideo, tvTimeUp, tvCountViews, tvCountLiked, tvNameChannel,
            tvNumberSubscriber, tvSubscribe, tvNumberComment, tvSubscribed;
    ImageView ivLiked, ivDisliked, ivShare, ivDownload, ivSave, ivAvtChannel;
    RecyclerView rvListVideoPlay;
    YouTubePlayer ypPlayItemClick;
    ImageView ivOpenDescription;
    CheckBox cbNotificationChannel;
    public ConstraintLayout clComment;
    AdapterMainVideoYoutube adapterListVideoYoutube;
    private String viewer;
    ArrayList<String> listViewer = new ArrayList<>();
    public static ArrayList<ItemVideoMain> listPlayRelate = new ArrayList<>();

    private boolean numberLikeCheck = true;
    private String id = "";
    private String idPlayListInItemVideo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_play_video);

        mapping();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        testDisplayTvSubscribe();
        Intent getDataInMain = getIntent();
        ItemVideoMain itemData = (ItemVideoMain) getDataInMain
                .getSerializableExtra(VALUE_ITEM_VIDEO);
//        Toast.makeText(this, itemData.getTvTitleVideo()+"", Toast.LENGTH_SHORT).show();
        id = itemData.getIdVideo();
        tvTimeUp.setText(itemData.getTvTimeUp());
        tvTitleVideo.setText(itemData.getTvTitleVideo());
        tvCountViews.setText(itemData.getTvViewCount());
        tvCountLiked.setText(itemData.getLikeCount());
        tvNumberComment.setText(itemData.getTvCommentCount());
        tvNameChannel.setText(itemData.getTvNameChannel());
        tvNumberSubscriber.setText(itemData.getNumberSubscribe());
//        Toast.makeText(this, itemData.getIvVideo()+"", Toast.LENGTH_SHORT).show();
        Picasso.get().load(itemData.getUrlAvtChannel()).into(ivAvtChannel);
        ypViewPlay.initialize(API_KEY, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListVideoPlay.setLayoutManager(linearLayoutManager);

        Toast.makeText(this, FragmentHome.listPlayRelated.size()+"", Toast.LENGTH_SHORT).show();
        adapterListVideoYoutube =
                new AdapterMainVideoYoutube(FragmentHome.listItemVideo,
                        new InterfaceClickItemMainVideo() {
                            @Override
                            public void onClickItemVideoMainVideo(int position) {
                                idPlayListInItemVideo = FragmentHome.listItemVideo.get(position).getIdVideo();
                                tvTimeUp.setText(FragmentHome.listItemVideo.get(position).getTvTimeUp());
                                tvTitleVideo.setText(FragmentHome.listItemVideo.get(position).getTvTitleVideo());
                                tvCountViews.setText(FragmentHome.listItemVideo.get(position).getTvViewCount());
                                tvCountLiked.setText(FragmentHome.listItemVideo.get(position).getLikeCount());
                                tvNumberComment.setText(FragmentHome.listItemVideo.get(position).getTvCommentCount());
                                tvNameChannel.setText(FragmentHome.listItemVideo.get(position).getTvNameChannel());
//        Toast.makeText(this, FragmentHome.listItemVideo.get(position).getIvVideo()+"", Toast.LENGTH_SHORT).show();
                                Picasso.get().load(FragmentHome.listItemVideo.get(position)
                                        .getUrlAvtChannel()).into(ivAvtChannel);
                                Log.d("AAAAAAAAAAAAAAAA", FragmentHome.listItemVideo
                                        .get(position).getUrlAvtChannel() + "");
                                ypPlayItemClick.loadVideo(idPlayListInItemVideo);
//                Toast.makeText(ActivityPlayVideo.this, idPlayListInItemVideo+"", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onClickMenuItemMainVideo(int position) {

                            }
                        });
        rvListVideoPlay.setAdapter(adapterListVideoYoutube);
        adapterListVideoYoutube.notifyDataSetChanged();
        clComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberLikeCheck) {
                    ivLiked.setImageResource(drawable.ic_like_on);
                    ivDisliked.setImageResource(drawable.ic_dislike);
                    numberLikeCheck = false;
                } else {
                    ivLiked.setImageResource(drawable.ic_like);
                    numberLikeCheck = true;
                }
            }
        });

        ivDisliked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberLikeCheck == false) {
                    ivDisliked.setImageResource(drawable.ic_dislike_on);
                    ivLiked.setImageResource(drawable.ic_like);
                    numberLikeCheck = true;
                } else {
                    ivDisliked.setImageResource(drawable.ic_dislike);
                    ivLiked.setImageResource(drawable.ic_like);
                    numberLikeCheck = false;
                }
            }
        });

        tvSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSubscribe.setVisibility(View.GONE);
                tvSubscribed.setVisibility(View.VISIBLE);
                cbNotificationChannel.setVisibility(View.VISIBLE);
                cbNotificationChannel.setOnCheckedChangeListener(
                        new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    Snackbar snackbar = Snackbar
                                            .make(cbNotificationChannel,
                                                    "Notifications turned on",
                                                    Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }
                            }
                        });
            }
        });

        tvSubscribed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setMessage("Unsubscribe from pike channel ?");
                alertDialog.setPositiveButton("UNSUBSCRIBE",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvSubscribe.setVisibility(View.VISIBLE);
                                tvSubscribed.setVisibility(View.GONE);
                                cbNotificationChannel.setVisibility(View.GONE);
                            }
                        });
                alertDialog.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                alertDialog.show();
            }
        });
    }



//    public void getViewer(String id){
//        String view = "";
//        String API_VIEWER = "https://youtube.googleapis.com/youtube/v3/videos?part=statistics&id="
//                +id+"&key="+API_KEY+"";
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
//                API_VIEWER, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray jsonItems = response.getJSONArray(ITEMS);
//                    JSONObject jsonItem = jsonItems.getJSONObject(0);
//                    JSONObject jsonStatics = jsonItem.getJSONObject(STATISTICS);
//                    listViewer.add(FragmentHome.formatViewer(Integer.parseInt(jsonStatics.getString(VIEW_COUNT))));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ActivityPlayVideo.this, error+"", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }
//
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String formatTimeUpVideo(String time) {
        String timeEnd = java.time.Clock.systemUTC().instant().toString();
        String timeStart = time;
        Instant start = Instant.parse(timeStart);
        Instant end = Instant.parse(timeEnd);

        long duration = Duration.between(start, end).toMillis();
        int hour = (int) TimeUnit.MILLISECONDS.toHours(duration);
        int min = (int) (TimeUnit.MILLISECONDS.toMinutes(duration)
                - TimeUnit.MILLISECONDS.toHours(duration) * 60);
//        int second = (int) (TimeUnit.MILLISECONDS.toSeconds(duration) - minutes);
        String timeUp = "";
        if (hour > 8760) {
            timeUp = (hour / 8760) + " year ago";
        }
        if (hour > 720 && hour < 8760) {
            timeUp = (hour / 720) + " month ago";
        }
        if (hour > 168 && hour < 720) {
            timeUp = (hour / 168) + " week ago";
        }
        if (hour < 168 && hour > 24) {
            timeUp = (hour / 24) + " day ago";
        }
        if (hour > 1 && hour < 24) {
            timeUp = (hour) + " hour ago";
        }
        if (hour < 1) {
            timeUp = min + "min ago";
        }
        return timeUp;
    }


    public void testDisplayTvSubscribe() {
        if (tvSubscribe.getVisibility() == View.VISIBLE) {
            tvSubscribed.setVisibility(View.GONE);
            cbNotificationChannel.setVisibility(View.GONE);
        } else {
            tvSubscribed.setVisibility(View.VISIBLE);
            tvSubscribe.setVisibility(View.GONE);
            cbNotificationChannel.setVisibility(View.VISIBLE);
        }
    }

    public void mapping() {
        tvSubscribe = findViewById(R.id.tv_play_video_subscribe);
        tvCountLiked = findViewById(R.id.tv_like_toolbar);
        tvCountViews = findViewById(R.id.tv_play_video_count_viewer);
        tvTitleVideo = findViewById(R.id.tv_title_video_play);
        tvTimeUp = findViewById(R.id.tv_play_video_count_time);
        tvNameChannel = findViewById(R.id.tv_play_item_name_channel);
        tvNumberSubscriber = findViewById(R.id.tv_play_item_count_subscribe);
        tvNumberComment = findViewById(R.id.tv_number_comment);
        tvSubscribed = findViewById(R.id.tv_play_video_subscribed);
        ivAvtChannel = findViewById(R.id.iv_avt_channel_play);
        ivLiked = findViewById(R.id.iv_ic_like_play_video);
        ivDisliked = findViewById(R.id.iv_ic_dislike_play_video);
        ivOpenDescription = findViewById(R.id.iv_icon_down_description);
        cbNotificationChannel = findViewById(R.id.cb_on_notification_channel);
        rvListVideoPlay = findViewById(R.id.rv_list_play_video);
        ypViewPlay = findViewById(R.id.yp_video_main);
        clComment = findViewById(R.id.cl_comment);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        @NonNull YouTubePlayer youTubePlayer, boolean b) {
        ypPlayItemClick = youTubePlayer;
        ypPlayItemClick.loadVideo(id);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, youTubeInitializationResult
                + " LOI ROI CU", Toast.LENGTH_SHORT).show();
    }
}