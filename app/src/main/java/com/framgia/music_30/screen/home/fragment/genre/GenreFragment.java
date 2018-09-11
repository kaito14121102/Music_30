package com.framgia.music_30.screen.home.fragment.genre;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Genre;
import com.framgia.music_30.screen.songgenre.SongGenreActivity;

import java.util.ArrayList;
import java.util.List;

public class GenreFragment extends Fragment implements GenreAdapter.OnItemClickListener, GenreContract.View {
    private GenreContract.Presenter mPresenter;
    private GenreAdapter mGenreAdapter;

    public static GenreFragment newInstance() {
        GenreFragment genreFragment = new GenreFragment();
        return genreFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);
        initViews(view);
        initData();
        return view;
    }

    @Override
    public void onGenreClicked(String typeGenre) {
        startActivity(SongGenreActivity.getGenreIntent(getActivity(), typeGenre));
    }

    @Override
    public void setPresenter(GenreContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void OnGetDataSuccess(List<Genre> genres) {
        mGenreAdapter.addData(genres);
    }

    private void initViews(View view) {
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recycle_view_genre);
        setupRecycler(recyclerViewGenre);
    }

    private void setupRecycler(RecyclerView recyclerView) {
        ArrayList<Genre> genres;
        genres = new ArrayList<>();
        mGenreAdapter = new GenreAdapter(getActivity(), genres, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mGenreAdapter);
    }

    private void initData() {
        mPresenter.getGenre();
    }
}
