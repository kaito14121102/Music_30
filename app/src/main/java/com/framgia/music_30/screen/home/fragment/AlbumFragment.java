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

public class AlbumFragment extends Fragment {

    public static AlbumFragment newInstance() {
        AlbumFragment albumFragment = new AlbumFragment();
        return albumFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recycle_view_album);
        setupRecycler(recyclerViewGenre);
    }

    private void setupRecycler(RecyclerView recyclerView) {
        ArrayList<Genre> genreArrayList = new ArrayList<>();
        genreArrayList.add(new Genre(R.drawable.all_song));
        genreArrayList.add(new Genre(R.drawable.classical));
        AlbumAdapter albumAdapter = new AlbumAdapter(getActivity(), genreArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(albumAdapter);
    }
}
