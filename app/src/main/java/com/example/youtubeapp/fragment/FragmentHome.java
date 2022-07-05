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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


public class FragmentHome extends Fragment implements InterfaceDefaultValue {
    public static ArrayList<ItemVideoMain> listItemVideo = new ArrayList<>();
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
        adapterMainVideoYoutube = new AdapterMainVideoYoutube(listItemVideo,
                new InterfaceClickItemMainVideo() {
                    @Override
                    public void onClickItemVideoMainVideo(int position) {
                        Intent intentMainToPlayVideo =
                                new Intent(getContext(), ActivityPlayVideo.class);
                        intentMainToPlayVideo.putExtra(VALUE_ITEM_VIDEO,
                                listItemVideo.get(position));
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

        getJsonApiYoutube();
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
//                                Log.d("Comment Count"+i, commentCount);
                                listItemVideo.add(new ItemVideoMain(titleVideo,
                                        urlThumbnail, idChannel, channelName,
                                        viewCount, publishedAt, idVideo,
                                        commentCount, numberLiker));

                                adapterMainVideoYoutube.notifyDataSetChanged();
                                pbLoadListVideoMain.setVisibility(View.GONE);
                                getUrlAvtNbSubscribeChannel(idChannel, i);
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

    private void getUrlAvtNbSubscribeChannel(String ID_CHANNEL, int position) {
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
                    listItemVideo.get(position).setUrlAvtChannel(urlChannel);
                    //Log.d("LINK "+position, jsonHigh.getString(URL));
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

    public String formatViewer(int value) {
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

    public void mapping(@NonNull View view) {
        ImageView ivMenuItemVideoMain = view.findViewById(R.id.iv_item_main_menu_vertical);
        pbLoadListVideoMain = view.findViewById(R.id.pb_load_list_video_main);
        rvListHotKeys = view.findViewById(R.id.lv_hot_keywords);
        rvListVideoMain = view.findViewById(R.id.rv_list_video_main);
    }
}
