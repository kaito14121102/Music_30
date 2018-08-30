package com.framgia.music_30.data.source;

import com.framgia.music_30.data.model.Genre;
import com.framgia.music_30.data.source.local.GenreLocalDataSource;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;

public class GenreRepository {
    private static GenreRepository sInstance;
    private GenreLocalDataSource mGenreLocalDataSource;

    public GenreRepository(GenreLocalDataSource genreLocalDataSource) {
        mGenreLocalDataSource = genreLocalDataSource;
    }

    public static GenreRepository getInstance(GenreLocalDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new GenreRepository(localDataSource);
        }
        return sInstance;
    }

    public void getData(OnFetchDataJsonListener<Genre> listener) {
        mGenreLocalDataSource.getGenre(listener);
    }
}
