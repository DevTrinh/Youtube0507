package com.example.youtubeapp.item;

import com.example.youtubeapp.interfacee.InterfaceDefaultValue;

import java.io.Serializable;

public class ItemVideoMain implements Serializable, InterfaceDefaultValue {


    private String titleVideo;
    private String urlThumbnail;

    public String getTvTitleVideo() {
        return tvTitleVideo;
    }

    public void setTvTitleVideo(String tvTitleVideo) {
        this.tvTitleVideo = tvTitleVideo;
    }

    public String getIvVideo() {
        return ivVideo;
    }

    public void setIvVideo(String ivVideo) {
        this.ivVideo = ivVideo;
    }

    public String getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(String idChannel) {
        this.idChannel = idChannel;
    }

    public String getTvNameChannel() {
        return tvNameChannel;
    }

    public void setTvNameChannel(String tvNameChannel) {
        this.tvNameChannel = tvNameChannel;
    }

    public String getTvViewCount() {
        return tvViewCount;
    }

    public void setTvViewCount(String tvViewCount) {
        this.tvViewCount = tvViewCount;
    }

    public String getTvTimeUp() {
        return tvTimeUp;
    }

    public void setTvTimeUp(String tvTimeUp) {
        this.tvTimeUp = tvTimeUp;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getTvCommentCount() {
        return tvCommentCount;
    }

    public void setTvCommentCount(String tvCommentCount) {
        this.tvCommentCount = tvCommentCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getUrlAvtChannel() {
        return urlAvtChannel;
    }

    public void setUrlAvtChannel(String urlAvtChannel) {
        this.urlAvtChannel = urlAvtChannel;
    }

    public String getNumberSubscribe() {
        return numberSubscribe;
    }

    public void setNumberSubscribe(String numberSubscribe) {
        this.numberSubscribe = numberSubscribe;
    }

    private String tvTitleVideo;
    private String ivVideo;
    private String idChannel;
    private String channelName;
    private String viewCount;
    private String tvNameChannel;
    private String tvViewCount;
    private String tvTimeUp;
    private String publishedAt;
    private String idVideo;
    private String tvCommentCount;
    private String likeCount;
    private String urlAvtChannel;
    private String numberSubscribe;

    public ItemVideoMain(String tvTitleVideo, String ivVideo, String idChannel,
                         String tvNameChannel, String tvViewCount, String tvTimeUp,
                         String idVideo, String tvCommentCount, String likeCount) {
        this.tvTitleVideo = tvTitleVideo;
        this.ivVideo = ivVideo;
        this.idChannel = idChannel;
        this.tvNameChannel = tvNameChannel;
        this.tvViewCount = tvViewCount;
        this.tvTimeUp = tvTimeUp;
        this.idVideo = idVideo;
        this.tvCommentCount = tvCommentCount;
        this.likeCount = likeCount;
    }
}