package com.framgia.music_30.screen.search;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.SongRepository;
import com.framgia.music_30.data.source.local.SongLocalDataSource;
import com.framgia.music_30.data.source.remote.SongRemoteDataSource;
import com.framgia.music_30.screen.player.PlayerActivity;
import com.framgia.music_30.screen.player.PlayerSongService;
import com.framgia.music_30.screen.songgenre.SongGenreActivity;
import com.framgia.music_30.screen.songgenre.SongGenreAdapter;
import com.framgia.music_30.screen.songgenre.SongGenrePresenter;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements SearchContract.View
        , SearchAdapter.OnItemClickListener {
    public static final String TEXT_SEARCH = "com.framgia.music_30_text_search";
    private String mTextSearch;
    private SearchAdapter mAdapter;
    private ArrayList<Song> mSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getTextSearch();
        initViews();
        initData(mTextSearch);

    }

    public static Intent getIntentSearch(Context context, String textSearch) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(TEXT_SEARCH, textSearch);
        return intent;
    }


    @Override
    public void onGetDataSuccess(List<Song> songList) {
        if (songList != null) {
            mSongs.addAll(songList);
            mAdapter.addData(songList);
        }
    }

    @Override
    public void onGetDataError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSongClicked(int position) {
        startService(PlayerSongService.getIntentService(this, position, mSongs));
        startActivity(new Intent(SearchActivity.this, PlayerActivity.class));
    }

    private void initData(String textSearch) {
        SongRemoteDataSource remoteDataSource = SongRemoteDataSource.getInstance();
        SongLocalDataSource localDataSource = SongLocalDataSource.getInstance();
        SongRepository songRepository = SongRepository.getInstance(remoteDataSource, localDataSource);
        SearchPresenter presenter = new SearchPresenter(songRepository);
        presenter.setView(this);
        presenter.getSong(textSearch);
    }

    private void initViews() {
        mSongs = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycle_view_search);
        setupRecycler(recyclerView);
    }

    private void setupRecycler(RecyclerView recyclerView) {
        mAdapter = new SearchAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    private void getTextSearch() {
        Intent intent = getIntent();
        mTextSearch = intent.getStringExtra(TEXT_SEARCH);
    }
}
