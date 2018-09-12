package com.framgia.music_30.screen.songgenre;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.SongRepository;
import com.framgia.music_30.data.source.local.SongLocalDataSource;
import com.framgia.music_30.data.source.remote.SongRemoteDataSource;
import com.framgia.music_30.screen.player.PlayerActivity;
import com.framgia.music_30.screen.player.PlayerSongService;

import java.util.ArrayList;
import java.util.List;

public class SongGenreActivity extends AppCompatActivity implements SongGenreContract.View
        , SongGenreAdapter.OnItemClickListener {
    public static final String TYPE_GENRE = "com.framgia.music_30.TYPE_GENRE";
    public static final String SONG_LIST = "com.framgia.music_30.SONG_LIST";
    public static final String SONG_POSITION = "com.framgia.music_30.SONG_POSITION";
    private SongGenreAdapter mAdapter;
    private ArrayList<Song> mSongs;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private ImageView mImageGenre;

    public static Intent getGenreIntent(Context context, String typeGenre) {
        Intent intent = new Intent(context, SongGenreActivity.class);
        intent.putExtra(TYPE_GENRE, typeGenre);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_genre);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        getTypeGenre();
    }

    @Override
    public void onGetDataSuccess(List<Song> songs) {
        mSongs.addAll(songs);
        mAdapter.addData(songs);

    }

    @Override
    public void onGetDataError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSongClicked(int position) {
        startService(PlayerSongService.getIntentService(this, position, mSongs));
        startActivity(new Intent(SongGenreActivity.this, PlayerActivity.class));
    }

    private void getTypeGenre() {
        String genre = getIntent().getStringExtra(TYPE_GENRE);
        if(genre.equals(R.string.GENRE_ALL_MUSIC)){
            mCollapsingToolbarLayout.setTitle("All Music");
            mImageGenre.setImageResource(R.drawable.allmusic);
        }
        initData(genre);
    }

    private void initData(String genre) {
        SongRemoteDataSource remoteDataSource = SongRemoteDataSource.getInstance();
        SongLocalDataSource localDataSource = SongLocalDataSource.getInstance();
        SongRepository songRepository = SongRepository.getInstance(remoteDataSource, localDataSource);
        SongGenrePresenter presenter = new SongGenrePresenter(songRepository);
        presenter.setView(this);
        presenter.getSong(genre);
    }

    private void initViews() {
        mToolbar = findViewById(R.id.tool_bar);
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mSongs = new ArrayList<>();
        mImageGenre = findViewById(R.id.image_genre);
        RecyclerView recyclerViewGenre = findViewById(R.id.recycle_view_song);
        setupRecycler(recyclerViewGenre);
    }

    private void setupRecycler(RecyclerView recyclerView) {
        mAdapter = new SongGenreAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }
}
