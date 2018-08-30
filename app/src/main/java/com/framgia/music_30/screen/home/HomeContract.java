package com.framgia.music_30.screen.home;

import com.framgia.music_30.data.model.Genre;
import com.framgia.music_30.ultil.BaseView;

import java.util.ArrayList;
import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void OnGetDataSuccess(List<Genre> genreList);
    }

    interface Presenter {
        void getGenre();
    }
}
