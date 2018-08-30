package com.framgia.music_30.data.source.local;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Genre;
import com.framgia.music_30.data.source.GenreDataSource;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_30.ultil.Constant;

import java.util.ArrayList;
import java.util.List;

public class GenreLocalDataSource implements GenreDataSource.Local {
    private static GenreLocalDataSource sInstance;

    public GenreLocalDataSource() {
    }

    public static GenreLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new GenreLocalDataSource();
        }
        return sInstance;
    }

    @Override
    public void getGenre(OnFetchDataJsonListener<Genre> listener) {
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(new Genre(R.drawable.all_song, Constant.GENRE_ALL_MUSIC));
        genres.add(new Genre(R.drawable.all_song, Constant.GENRE_ALL_AUDIO));
        genres.add(new Genre(R.drawable.classical, Constant.GENRE_CLASSICAL));
        genres.add(new Genre(R.drawable.classical, Constant.GENRE_COUNTRY));
        genres.add(new Genre(R.drawable.classical, Constant.GENRE_AMBIENT));
        genres.add(new Genre(R.drawable.classical, Constant.GENRE_ALTERNATIVEROCK));
        listener.onSucess(genres);
    }
}
