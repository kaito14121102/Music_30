package com.framgia.music_30.data.source.remote;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.SongDataSource;

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
    public void getData(OnFetchDataJsonListener<Song> listener) {
    }
}
