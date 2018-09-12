package com.framgia.music_30.screen.search;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.SongRepository;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;
    private SongRepository mSongRepository;

    public SearchPresenter(SongRepository songRepository) {
        mSongRepository = songRepository;
    }

    @Override
    public void getSong(String textSearch) {
        mSongRepository.getDataSearch(textSearch, new OnFetchDataJsonListener<Song>() {
            @Override
            public void onSucess(List<Song> data) {
                mView.onGetDataSuccess(data);
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }

    @Override
    public void setView(SearchContract.View view) {
        mView = view;
    }
}
