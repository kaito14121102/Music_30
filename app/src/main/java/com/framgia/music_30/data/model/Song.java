package com.framgia.music_30.data.model;

public class Song {
    private String mId;
    private String mTitle;
    private String mImageSong;
    private String mUrlPlay;
    private String mUrlDownLoad;
    private String mType;

    public Song() {
    }

    public Song(String id, String title, String imageSong, String urlPlay, String urlDownLoad, String type) {
        mId = id;
        mTitle = title;
        mImageSong = imageSong;
        mUrlPlay = urlPlay;
        mUrlDownLoad = urlDownLoad;
        mType = type;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getImageSong() {
        return mImageSong;
    }

    public void setImageSong(String imageSong) {
        mImageSong = imageSong;
    }

    public String getUrlPlay() {
        return mUrlPlay;
    }

    public void setUrlPlay(String urlPlay) {
        mUrlPlay = urlPlay;
    }

    public String getUrlDownLoad() {
        return mUrlDownLoad;
    }

    public void setUrlDownLoad(String urlDownLoad) {
        mUrlDownLoad = urlDownLoad;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
