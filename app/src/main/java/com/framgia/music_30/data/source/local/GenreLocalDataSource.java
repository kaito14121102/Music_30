package com.framgia.music_30.data.source.local;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Genre;
import com.framgia.music_30.data.source.GenreDataSource;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_30.ultil.Constant;

import java.util.ArrayList;


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
        genres.add(new Genre(R.drawable.allmusic1, Constant.GENRE_ALL_MUSIC));
        genres.add(new Genre(R.drawable.allaudio1, Constant.GENRE_ALL_AUDIO));
        genres.add(new Genre(R.drawable.classical1, Constant.GENRE_CLASSICAL));
        genres.add(new Genre(R.drawable.country2, Constant.GENRE_COUNTRY));
        genres.add(new Genre(R.drawable.ambient1, Constant.GENRE_AMBIENT));
        genres.add(new Genre(R.drawable.rock1, Constant.GENRE_ALTERNATIVEROCK));
        listener.onSucess(genres);
    }
}
