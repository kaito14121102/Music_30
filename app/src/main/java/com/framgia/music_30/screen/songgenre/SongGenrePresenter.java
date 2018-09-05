package com.framgia.music_30.screen.songgenre;

import com.framgia.music_30.data.model.Song;

import java.util.List;

public class SongGenrePresenter implements SongGenreContract.Presenter {
    private SongGenreContract.View mView;

    @Override
    public void getSong(String genre) {
    }

    @Override
    public void setView(SongGenreContract.View view) {
        mView = view;
    }
}
