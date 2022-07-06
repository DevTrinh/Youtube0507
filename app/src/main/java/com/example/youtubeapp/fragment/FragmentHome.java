package com.example.youtubeapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.youtubeapp.ActivityPlayVideo;
import com.example.youtubeapp.R;
import com.example.youtubeapp.adapter.AdapterListHotKeys;
import com.example.youtubeapp.adapter.AdapterMainVideoYoutube;
import com.example.youtubeapp.interfacee.InterfaceClickItemMainVideo;
import com.example.youtubeapp.interfacee.InterfaceDefaultValue;
import com.example.youtubeapp.item.ItemVideoMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class FragmentHome extends Fragment implements InterfaceDefaultValue, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout rfMain;
    public static ArrayList<ItemVideoMain> listItemVideo = new ArrayList<>();
    public static ArrayList<ItemVideoMain> listPlayRelated = new ArrayList<>();
    private ProgressBar pbLoadListVideoMain;
    public RecyclerView rvListVideoMain, rvListHotKeys;
    public static AdapterListHotKeys adapterListHotKeys;
    public static AdapterMainVideoYoutube adapterMainVideoYoutube;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,
                container, false);
        mapping(view);

        rfMain.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext());
        rvListVideoMain.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManagerHorizontal =
                new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        rvListHotKeys.setLayoutManager(linearLayoutManagerHorizontal);
        adapterListHotKeys = new AdapterListHotKeys(getListKey());
        rvListHotKeys.setAdapter(adapterListHotKeys);
        adapterListHotKeys.notifyDataSetChanged();
        getJsonApiYoutube();
        adapterMainVideoYoutube = new AdapterMainVideoYoutube(listItemVideo,
                new InterfaceClickItemMainVideo() {
                    @Override
                    public void onClickItemVideoMainVideo(int position) {
                        getUrlVideoRelated(listItemVideo.get(position).getIdVideo());
                        Intent intentMainToPlayVideo =
                                new Intent(getContext(), ActivityPlayVideo.class);
                        intentMainToPlayVideo.putExtra(VALUE_ITEM_VIDEO,
                                listItemVideo.get(position));
                        Log.d("asdddddddddddddddddd", listPlayRelated.size()+"");
                        startActivity(intentMainToPlayVideo);
                    }

                    @Override
                    public void onClickMenuItemMainVideo(int position) {
                        FragmentMenuItemVideoMain fragmentMenuItemVideoMain =
                                new FragmentMenuItemVideoMain();
                        fragmentMenuItemVideoMain.show(getActivity()
                                .getSupportFragmentManager(), getTag());
                    }
                });

        rvListVideoMain.setAdapter(adapterMainVideoYoutube);

        return view;
    }

    private void getJsonApiYoutube() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                API_YOUTUBE_MAIN_VIDEO, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String idVideo = "";
                            String titleVideo = "";
                            String publishedAt = "";
                            String idChannel = "";
                            String urlThumbnail = "";
                            String channelName = "";
                            String viewCount = "";
                            String numberLiker = "";
                            String commentCount = "";
                            String urlAvtChannel = "";
                            JSONArray jsonItems = response.getJSONArray(ITEMS);
                            Log.d("AAAAAAAAAAAAA", jsonItems.length() + "");
                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                idVideo = jsonItem.getString(ID);
//                                Log.d("ID: "+i, idVideo);
                                JSONObject jsonSnippet = jsonItem.getJSONObject(SNIPPET);
                                titleVideo = jsonSnippet.getString(TITLE);
//                                Log.d("Title: "+i, titleVideo);
                                channelName = jsonSnippet.getString(CHANNEL_TITLE);
//                                Log.d("Channel name "+i, channelName);
                                publishedAt = formatTimeUpVideo(jsonSnippet
                                        .getString(PUBLISHED_AT) + "");
//                                Log.d(PUBLISHED_AT+i,publishedAt);
                                idChannel = jsonSnippet.getString(CHANNEL_ID);
//                                Log.d("ID CHANNEL "+i, idChannel);
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject(THUMBNAIL);
                                JSONObject jsonStandard = jsonThumbnail.getJSONObject(HIGH);
                                urlThumbnail = jsonStandard.getString(URL);
//                                Log.d("THUMBNAIL "+i, urlThumbnail);
                                JSONObject jsonStatistics = jsonItem.getJSONObject(STATISTICS);
                                viewCount = formatViewer(jsonStatistics.getInt(VIEW_COUNT));
//                                Log.d("View Count: "+i, viewCount);
                                numberLiker = formatLiker(jsonStatistics.getInt(LIKED_COUNT));
//                                Log.d("Number like"+i,numberLiker);
                                if (jsonStatistics.has(COMMENT_COUNT)) {
                                    commentCount = formatComment(Integer
                                            .parseInt(jsonStatistics.getString(COMMENT_COUNT)));
                                }
                                getUrlAvtNbSubscribeChannel(listItemVideo, idChannel, i);
//                                Log.d("Comment Count"+i, commentCount);
                                listItemVideo.add(new ItemVideoMain(titleVideo,
                                        urlThumbnail, idChannel, channelName,
                                        viewCount, publishedAt, idVideo,
                                        commentCount, numberLiker));
                                adapterMainVideoYoutube.notifyDataSetChanged();
                                pbLoadListVideoMain.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void getUrlVideoRelated(String idRelated){
        String API_RELATED_VIDEO = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&relatedToVideoId="
                +idRelated+"&type=video&key="+API_KEY+"";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                API_RELATED_VIDEO, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray(ITEMS);
                            Log.d("LENGTHHHHHH", jsonItems.length()+"");
                            String idVideo = "";
                            String titleVideo = "Best Relaxing Piano Studio Ghibli Complete Collection 2022 (No Mid-roll Ads)";
                            String publishedAt = "";
                            String idChannel = "";
                            String urlThumbnail = "";
                            String channelName = "";
                            String viewCount = "2.725 lượt xem";
                            String numberLiker = "";
                            String commentCount = "";
                            for (int i = 0; i<jsonItems.length(); i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonIdVideo = jsonItem.getJSONObject(ID);
                                idVideo = jsonIdVideo.getString(ID_VIDEO);
//                        Log.d("ID", idVideo+"");
                                if (jsonItem.has(SNIPPET)){
                                    JSONObject jsonSnippet = jsonItem.getJSONObject(SNIPPET);
                                    titleVideo = jsonSnippet.getString(TITLE);
//                            Log.d("LOGGG"+i, titleVideo+"");
                                    idChannel = jsonSnippet.getString(CHANNEL_ID);
//                            Log.d("ID CHANNEL "+i, idChannel+"");
                                    JSONObject jsonThumbnail = jsonSnippet.getJSONObject(THUMBNAIL);
                                    JSONObject jsonHighImg = jsonThumbnail.getJSONObject(HIGH);
                                    urlThumbnail = jsonHighImg.getString(URL);
//                            Log.d("IMAGE "+i, urlThumbnail);
                                    channelName = jsonSnippet.getString(CHANNEL_TITLE);
                                    Log.d("CHANNEL NAME: "+i, channelName+"");
                                    publishedAt = formatTimeUpVideo(jsonSnippet
                                            .getString(PUBLISHED_AT) + "");
                                    Log.d(PUBLISHED_AT, publishedAt+"");
//                            getViewer(idRelated);
//                            Log.d("JSON STATICS: ", listViewer.size()+"")
                                }
                                listPlayRelated.add(new ItemVideoMain(titleVideo,
                                        urlThumbnail, idChannel,
                                        channelName, viewCount,
                                        publishedAt, idVideo,
                                        commentCount, numberLiker));
                                adapterMainVideoYoutube.notifyDataSetChanged();
                            };
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error+"",
                        Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void getUrlAvtNbSubscribeChannel(ArrayList<ItemVideoMain> listItemVideo, String ID_CHANNEL, int position) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2Cstatistics&id="
                        + ID_CHANNEL + "&key=" + API_KEY + "",
                null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    String urlChannel = "";
                    JSONArray jsonItems = response.getJSONArray(ITEMS);
                    JSONObject jsonItem = jsonItems.getJSONObject(0);
                    JSONObject jsonSnippet = jsonItem.getJSONObject(SNIPPET);
                    JSONObject jsonThumbnail = jsonSnippet.getJSONObject(THUMBNAIL);
                    JSONObject jsonHigh = jsonThumbnail.getJSONObject(HIGH);
                    urlChannel = jsonHigh.getString(URL);
                    listItemVideo.get(position).setUrlAvtChannel(jsonHigh.getString(URL)+"");
//                    Log.d("LINK "+position, jsonHigh.getString(URL));
                    JSONObject jsonStatics = jsonItem.getJSONObject(STATISTICS);
                    listItemVideo.get(position).setNumberSubscribe(formatSubscribe
                            (Integer.parseInt(jsonStatics.getString(SUBSCRIBE_COUNT))));
//                    Log.d("AAAAA " + position, urlChannel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "FALSE GET URL AVT CHANNEL",
                        Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @NonNull
    private ArrayList<String> getListKey() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Rap Việt");
        list.add("No nut november");
        list.add("Social Slang");
        list.add("Running Man Việt Nam");
        list.add("Body me");
        list.add("Đội tuyển U23 Việt Nam");
        list.add("Chảy nước miếng");
        list.add("Vui đi, chờ chi cuối tuầ");
        list.add("Không phải tại nó");
        return list;
    }

    @NonNull
    public static String formatViewer(int value) {
        String arr[] = {"", "K", "M", "B", "T", "P", "E"};
        int index = 0;
        while ((value / 1000) >= 1) {
            value = value / 1000;
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.format("%s%s Views", decimalFormat.format(value), arr[index]);
    }

    public String formatComment(int value) {
        String arr[] = {"", "K", "M", "B", "T", "P", "E"};
        int index = 0;
        while ((value / 1000) >= 1) {
            value = value / 1000;
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.format("%s%s", decimalFormat.format(value), arr[index]);
    }

    public String formatLiker(int value) {
        String arr[] = {"", "K", "M", "B", "T", "P", "E"};
        int index = 0;
        while ((value / 1000) >= 1) {
            value = value / 1000;
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.format("%s%s ", decimalFormat.format(value), arr[index]);
    }

    public String formatSubscribe(int value) {
        String arr[] = {"", "K", "M", "B", "T", "P", "E"};
        int index = 0;
        while ((value / 1000) >= 1) {
            value = value / 1000;
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.format("%s%s Subscribe", decimalFormat.format(value), arr[index]);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatTimeUpVideo(String time) {
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

    public void mapping(@NonNull View view) {
        rfMain = view.findViewById(R.id.rf_layout_main);
        ImageView ivMenuItemVideoMain = view.findViewById(R.id.iv_item_main_menu_vertical);
        pbLoadListVideoMain = view.findViewById(R.id.pb_load_list_video_main);
        rvListHotKeys = view.findViewById(R.id.lv_hot_keywords);
        rvListVideoMain = view.findViewById(R.id.rv_list_video_main);
    }

    @Override
    public void onRefresh() {

    }
}
