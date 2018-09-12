package com.framgia.music_30.data.source;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;

public interface SongDataSource {
    interface RemoteDataSource {
        void getData(String genre, OnFetchDataJsonListener<Song> listener);

        void getDataSearch(String textSearch, OnFetchDataJsonListener<Song> listener);
    }

    interface LocalDataSource {
        void getData(OnFetchDataJsonListener<Song> listener);
    }
}
