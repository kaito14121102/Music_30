package com.framgia.music_30.screen.home.fragment.genre;

import com.framgia.music_30.data.model.Genre;
import com.framgia.music_30.data.source.GenreRepository;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;

import java.util.List;

public class GenrePresenter implements GenreContract.Presenter {
    private GenreContract.View mView;
    private GenreRepository mGenreRepository;

    public GenrePresenter(GenreContract.View view, GenreRepository genreRepository) {
        mView = view;
        mGenreRepository = genreRepository;
        mView.setPresenter(this);
    }

    @Override
    public void getGenre() {
        mGenreRepository.getData(new OnFetchDataJsonListener<Genre>() {

            @Override
            public void onSucess(List<Genre> data) {
                mView.OnGetDataSuccess(data);
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }
}
