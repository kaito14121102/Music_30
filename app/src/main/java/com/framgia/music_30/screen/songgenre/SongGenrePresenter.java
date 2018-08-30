package com.framgia.music_30.screen.songgenre;

import android.util.Log;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.SongRepository;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;

import java.util.List;

public class SongGenrePresenter implements SongGenreContract.Presenter {
    private SongGenreContract.View mView;
    private SongRepository mSongRepository;

    public SongGenrePresenter(SongRepository songRepository) {
        mSongRepository = songRepository;
    }

    @Override
    public void getSong(String genre) {
        mSongRepository.getData(new OnFetchDataJsonListener<Song>() {
            @Override
            public void onSucess(List<Song> data) {
                mView.onGetDataSuccess(data);
            }
        });
    }

    @Override
    public void setView(SongGenreContract.View view) {
        mView = view;
    }
}
