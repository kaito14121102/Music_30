package com.framgia.music_30.screen.home.fragment.mysong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.screen.player.PlayerActivity;
import com.framgia.music_30.screen.player.PlayerSongService;

import java.util.ArrayList;
import java.util.List;

public class MySongsFragment extends Fragment implements MySongContract.View {
    private MySongContract.Presenter mPresenter;
    private MySongAdapter mMySongAdapter;

    public static MySongsFragment newInstance() {
        MySongsFragment mySongsFragment = new MySongsFragment();
        return mySongsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_mysongs, container, false);
        initViews(mView);
        initData();
        return mView;
    }

    @Override
    public void OnGetDataSuccess(List<Song> songs) {
        if (songs != null) {
            mMySongAdapter.addData(songs);
        }
    }

    @Override
    public void OnGetDataError(Exception e) {
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MySongContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void initViews(View view) {
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recycle_view_mysong);
        setupRecycler(recyclerViewGenre);
    }

    private void setupRecycler(RecyclerView recyclerView) {
        mMySongAdapter = new MySongAdapter(getActivity());
        recyclerView.setAdapter(mMySongAdapter);
    }

    public void initData() {
        mPresenter.getSong();
    }
}
