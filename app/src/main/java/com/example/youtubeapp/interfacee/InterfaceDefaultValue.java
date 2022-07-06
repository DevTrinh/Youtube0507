package com.example.youtubeapp.interfacee;

public interface InterfaceDefaultValue {
    //    String API_YOUTUBE_MAIN_VIDEO_VN = "https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&chart=mostPopular&maxResults=50&regionCode=VN&key=AIzaSyDA2oRYCsTyVd6u5y1ypfda19b-fQZ_7VQ";
    String API_YOUTUBE_MAIN_VIDEO = "https://youtube.googleapis.com/youtube/v3/videos?part=id&part=liveStreamingDetails&part=snippet&part=statistics&chart=mostPopular&maxResults=50&key=AIzaSyDApPAI-FnPALLgsycuy68iSWIYeRsSgug";
    String API_IMAGE_CHANNEL_YOUTUBE = "https://www.googleapis.com/youtube/v3/channels?part=snippet&id=UCwppdrjsBPAZg5_cUwQjfMQ&fields=items%2Fsnippet%2Fthumbnails&key=AIzaSyDA2oRYCsTyVd6u5y1ypfda19b-fQZ_7VQ";
    //    String infovideo  ... https://youtube.googleapis.com/youtube/v3/commentThreads?part=snippet%2Creplies&maxResults=100&videoId=_VB39Jo8mAQ&key=AIzaSyDA2oRYCsTyVd6u5y1ypfda19b-fQZ_7VQ
    String API_YOUTUBE_EXPLORE = "https://youtube.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&chart=mostPopular&maxResults=50&regionCode=VN&key=AIzaSyDA2oRYCsTyVd6u5y1ypfda19b-fQZ_7VQ";
    String API_KEY = "AIzaSyDApPAI-FnPALLgsycuy68iSWIYeRsSgug";
    String ITEMS = "items";
    String ID = "id";
    String SNIPPET = "snippet";
    String VALUE_SEARCH = "VALUE_SEARCH";
    String THUMBNAIL = "thumbnails";
    String STANDARD = "standard";
    String CHANNEL_TITLE ="channelTitle";
    String VIEW_COUNT = "viewCount";
    String LIKED_COUNT = "likeCount";
    String URL = "url";
    String ID_VIDEO = "videoId";
    String TITLE = "title";
    String PUBLISHED_AT = "publishedAt";
    String STATISTICS = "statistics";
    String VALUE_ITEM_VIDEO = "valueItemVideo";
    String COMMENT_COUNT = "commentCount";
    String CHANNEL_ID = "channelId";
    String HIGH = "high";
    String SUBSCRIBE_COUNT = "subscriberCount";
    String FRAGMENT_HOME = "FRAGMENT_HOME";
    String FRAGMENT_EXPLORE = "FRAGMENT_EXPLORE";
    String FRAGMENT_SUBSCRIPTION = "FRAGMENT_SUBSCRIPTION";
    String FRAGMENT_NOTIFICATION = "FRAGMENT_NOTIFICATION";
    String FRAGMENT_LIBRARY = "FRAGMENT_LIBRARY";
    String DATA_SEARCH = "DATA_SEARCH";
}