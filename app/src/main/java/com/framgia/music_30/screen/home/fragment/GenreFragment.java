package com.framgia.music_30.screen.home.fragment;


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

import java.util.ArrayList;

public class GenreFragment extends Fragment {

    public static GenreFragment newInstance() {
        GenreFragment genreFragment = new GenreFragment();
        return genreFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recycle_view_genre);
        setupRecycler(recyclerViewGenre);
    }

    private void setupRecycler(RecyclerView recyclerView) {
        ArrayList<Genre> genreArrayList = new ArrayList<>();
        addGenres(genreArrayList);
        GenreAdapter genreAdapter = new GenreAdapter(getActivity(), genreArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(genreAdapter);
    }

    void addGenres(ArrayList<Genre> genreArrayList){
        genreArrayList.add(new Genre(R.drawable.all_song,getString(R.string.GENRE_ALL_MUSIC)));
        genreArrayList.add(new Genre(R.drawable.all_song,getString(R.string.GENRE_ALL_AUDIO)));
        genreArrayList.add(new Genre(R.drawable.classical,getString(R.string.GENRE_CLASSICAL)));
        genreArrayList.add(new Genre(R.drawable.classical,getString(R.string.GENRE_COUNTRY)));
        genreArrayList.add(new Genre(R.drawable.classical,getString(R.string.GENRE_AMBIENT)));
        genreArrayList.add(new Genre(R.drawable.classical,getString(R.string.GENRE_ALTERNATIVEROCK)));
    }
}
