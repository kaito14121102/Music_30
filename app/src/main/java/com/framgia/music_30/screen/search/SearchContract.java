package com.framgia.music_30.screen.search;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.ultil.BasePresenter;

import java.util.List;

public interface SearchContract {
    interface View {
        void onGetDataSuccess(List<Song> songList);
        void onGetDataError(String error);
    }

    interface Presenter extends BasePresenter<SearchContract.View> {
        void getSong(String textSearch);
    }
}
