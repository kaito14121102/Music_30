package com.framgia.music_30.screen.home.fragment.mysong;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.screen.home.fragment.genre.GenreContract;
import com.framgia.music_30.ultil.BaseView;

import java.util.List;

public interface MySongContract {
    interface View extends BaseView<Presenter> {
        void OnGetDataSuccess(List<Song> songs);
    }

    interface Presenter {
        void getSong();
    }
}
