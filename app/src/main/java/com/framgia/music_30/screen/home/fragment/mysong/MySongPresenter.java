package com.framgia.music_30.screen.home.fragment.mysong;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.SongRepository;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;

import java.util.List;

public class MySongPresenter implements MySongContract.Presenter {
    private MySongContract.View mView;
    private SongRepository mSongRepository;

    public MySongPresenter(MySongContract.View view, SongRepository songRepository) {
        mView = view;
        mSongRepository = songRepository;
        mView.setPresenter(this);
    }

    @Override
    public void getSong() {
        mSongRepository.getDataOffline(new OnFetchDataJsonListener<Song>() {
            @Override
            public void onSucess(List<Song> data) {
                mView.OnGetDataSuccess(data);
            }

            @Override
            public void onError(Exception exception) {
                mView.OnGetDataError(exception);
            }
        });
    }
}
