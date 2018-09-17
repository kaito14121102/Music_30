package com.framgia.music_30.data.source;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.local.SongLocalDataSource;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_30.data.source.remote.SongRemoteDataSource;

public class SongRepository {
    private static SongRepository sInstance;
    private SongRemoteDataSource mSongRemoteDataSource;
    private SongLocalDataSource mSongLocalDataSource;

    private SongRepository(SongRemoteDataSource remoteDataSource, SongLocalDataSource localDataSource) {
        mSongRemoteDataSource = remoteDataSource;
        mSongLocalDataSource = localDataSource;
    }

    public static SongRepository getInstance(SongRemoteDataSource remoteDataSource, SongLocalDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new SongRepository(remoteDataSource, localDataSource);
        }
        return sInstance;
    }

    public void getData(String genre, OnFetchDataJsonListener<Song> listener) {
        mSongRemoteDataSource.getData(genre, listener);
    }

    public void getDataOffline(OnFetchDataJsonListener<Song> listener) {
        mSongLocalDataSource.getData(listener);
    }
}
