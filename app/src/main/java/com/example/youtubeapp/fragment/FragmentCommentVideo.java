package com.example.youtubeapp.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.youtubeapp.R;
import com.example.youtubeapp.adapter.AdapterListComment;
import com.example.youtubeapp.interfacee.InterfaceDefaultValue;
import com.example.youtubeapp.item.ItemComment;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentCommentVideo extends BottomSheetDialogFragment implements InterfaceDefaultValue {

    private CircleImageView ivAvtUserComment;
    private EditText etUserComment;
    private RecyclerView rvListComment;
    private AdapterListComment adapterListComment;
    private List<ItemComment> listCommentVideo;
    private String idVideo;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bt = (BottomSheetDialog) super
                .onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_comment_video, null);
        bt.setContentView(view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvListComment.setLayoutManager(linearLayoutManager);
        adapterListComment = new AdapterListComment(listCommentVideo);



        mapping(view);

        return bt;
    }

    private void mapping(View view) {
        ivAvtUserComment = view.findViewById(R.id.iv_avt_user_comment);
        etUserComment = view.findViewById(R.id.et_user_comment);
        rvListComment = view.findViewById(R.id.rv_list_comment_video);
    }

//    public void getJsonCommentVideo(String idVideoItem){
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
//                "https://youtube.googleapis.com/youtube/v3/commentThreads?part=snippet%2Creplies&maxResults=100&order=relevance&videoId="
//                        +idVideoItem+"&key="+API_KEY+"", null,
//                new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    for(int i = 0; i< response.length(); i++){
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//    }
}
