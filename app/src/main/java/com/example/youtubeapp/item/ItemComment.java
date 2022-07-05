package com.example.youtubeapp.item;

public class ItemComment {
    private String nameChannelComment;
    private String urlAvtChannelComment;
    private String timeComment;
    private String contentComment;
    private String numberLikeComment;

    public String getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(String replyComment) {
        this.replyComment = replyComment;
    }

    private String replyComment;

    public String getNameChannelComment() {
        return nameChannelComment;
    }

    public void setNameChannelComment(String nameChannelComment) {
        this.nameChannelComment = nameChannelComment;
    }

    public String getUrlAvtChannelComment() {
        return urlAvtChannelComment;
    }

    public void setUrlAvtChannelComment(String urlAvtChannelComment) {
        this.urlAvtChannelComment = urlAvtChannelComment;
    }

    public String getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(String timeComment) {
        this.timeComment = timeComment;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public String getNumberLikeComment() {
        return numberLikeComment;
    }

    public void setNumberLikeComment(String numberLikeComment) {
        this.numberLikeComment = numberLikeComment;
    }

    public ItemComment(String nameChannelComment, String urlAvtChannelComment,
                       String timeComment, String contentComment,
                       String numberLikeComment, String replyComment) {
        this.nameChannelComment = nameChannelComment;
        this.urlAvtChannelComment = urlAvtChannelComment;
        this.timeComment = timeComment;
        this.contentComment = contentComment;
        this.numberLikeComment = numberLikeComment;
        this.replyComment = replyComment;
    }
}
