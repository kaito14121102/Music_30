package com.framgia.music_30.data.model;

import android.widget.ImageView;

public class Genre {
    private int mImageGenre;

    public Genre() {
    }

    public Genre(int imageGenre) {
        mImageGenre = imageGenre;
    }

    public int getImageGenre() {
        return mImageGenre;
    }

    public void setImageGenre(int imageGenre) {
        mImageGenre = imageGenre;
    }
}
