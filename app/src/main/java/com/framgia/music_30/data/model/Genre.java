package com.framgia.music_30.data.model;

import android.widget.ImageView;

public class Genre {
    private int mImageGenre;
    private String mNameGenre;

    public Genre() {
    }

    public Genre(int imageGenre, String nameGenre) {
        mImageGenre = imageGenre;
        mNameGenre = nameGenre;
    }

    public int getImageGenre() {
        return mImageGenre;
    }

    public void setImageGenre(int imageGenre) {
        mImageGenre = imageGenre;
    }

    public String getNameGenre() {
        return mNameGenre;
    }

    public void setmNameGenre(String nameGenre) {
        mNameGenre = nameGenre;
    }
}
