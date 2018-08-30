package com.framgia.music_30.data.source;

import com.framgia.music_30.data.model.Genre;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;

public interface GenreDataSource {
    interface Local {
        void getGenre(OnFetchDataJsonListener<Genre> listener);
    }
}
