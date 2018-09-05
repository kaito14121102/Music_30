package com.framgia.music_30.data.source;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_30.data.source.remote.SongRemoteDataSource;

public class SongRepository {
    private static SongRepository sInstance;
    private SongRemoteDataSource mSongRemoteDataSource;

    private SongRepository(SongRemoteDataSource remoteDataSource) {
        mSongRemoteDataSource = remoteDataSource;
    }

    public static SongRepository getInstance(SongRemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new SongRepository(remoteDataSource);
        }
        return sInstance;
    }

    public void getData(OnFetchDataJsonListener<Song> listener) {
        mSongRemoteDataSource.getData(listener);
    }
}
