package com.framgia.music_30.data.model;

public class Song {
    String mTitle;
    String mUrl;

    public Song(String title, String url) {
        mTitle = title;
        mUrl = url;
    }

    public Song() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
