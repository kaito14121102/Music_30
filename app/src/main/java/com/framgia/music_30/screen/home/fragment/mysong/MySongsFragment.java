package com.framgia.music_30.screen.home.fragment.mysong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Genre;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.screen.home.fragment.genre.GenreAdapter;
import com.framgia.music_30.screen.player.PlayerActivity;
import com.framgia.music_30.screen.player.PlayerSongService;
import com.framgia.music_30.screen.songgenre.SongGenreActivity;

import java.util.ArrayList;
import java.util.List;

public class MySongsFragment extends Fragment implements MySongContract.View, MySongAdapter.OnItemClickListener {
    private MySongContract.Presenter mPresenter;
    private MySongAdapter mMySongAdapter;
    private ArrayList<Song> mSongs;
    private SwipeRefreshLayout mSwipeRefreshLayout;

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
        swipeEven();
        return mView;
    }

    @Override
    public void OnGetDataSuccess(List<Song> songs) {
        mSongs.addAll(songs);
        mMySongAdapter.addData(songs);
    }

    @Override
    public void setPresenter(MySongContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSongClicked(int position) {
        getActivity().startService(PlayerSongService.getIntentService(getActivity(), position, mSongs));
        startActivity(new Intent(getActivity(), PlayerActivity.class));
    }

    private void initViews(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        RecyclerView recyclerViewGenre = view.findViewById(R.id.recycle_view_mysong);
        setupRecycler(recyclerViewGenre);
    }

    private void setupRecycler(RecyclerView recyclerView) {
        mSongs = new ArrayList<>();
        mMySongAdapter = new MySongAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mMySongAdapter);
    }

    private void swipeEven() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                mSongs.clear();
                mPresenter.getSong();
            }
        });
    }

    public void initData() {
        mPresenter.getSong();
    }
}
