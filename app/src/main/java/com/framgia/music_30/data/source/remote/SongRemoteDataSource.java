package com.framgia.music_30.data.source.remote;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.SongDataSource;
import com.framgia.music_30.data.source.remote.fetchjson.DataJson;

public class SongRemoteDataSource implements SongDataSource.RemoteDataSource {
    private static SongRemoteDataSource sInstance;

    private SongRemoteDataSource() {
    }

    public static SongRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SongRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getData(String genre, OnFetchDataJsonListener<Song> listener) {
        new DataJson(listener).getData(genre);

    }
}
