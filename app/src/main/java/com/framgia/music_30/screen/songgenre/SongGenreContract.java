package com.framgia.music_30.screen.songgenre;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.ultil.BasePresenter;

import java.util.List;

public interface SongGenreContract {
    interface View {
        void onGetDataSuccess(List<Song> songList);
        void onGetDataError(String error);
    }

    interface Presenter extends BasePresenter<View> {
        void getSong(String genre);
    }
}
