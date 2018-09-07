package com.framgia.music_30.screen.player;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.screen.songgenre.SongGenreActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerSongService extends Service {
    private ArrayList<Song> mSongs;
    private int mPosition = 0;
    private PlayerManager mManager;
    public MediaPlayer mMediaPlayer;

    public static Intent getIntentService(Context context, int position, ArrayList<Song> songs) {
        Intent intent = new Intent(context, PlayerSongService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(SongGenreActivity.SONG_LIST, (Serializable) songs);
        intent.putExtras(bundle);
        intent.putExtra(SongGenreActivity.SONG_POSITION, position);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSongs = new ArrayList<>();
        Bundle bundle = intent.getExtras();
        mSongs = (ArrayList<Song>) bundle.getSerializable(SongGenreActivity.SONG_LIST);
        mPosition = intent.getIntExtra(SongGenreActivity.SONG_POSITION, 0);
        return super.onStartCommand(intent, flags, startId);
    }

    public void updateUiListener(UpdateUiPlayerListener listener) {
        mManager = new PlayerManager(mPosition, mSongs, mMediaPlayer, listener);
    }

    public void playSong() {
        mManager.playSong();
    }

    public void nextSong() {
        mManager.nextSong();
    }

    public void previousSong() {
        mManager.previousSong();
    }

    public void pauseSong() {
        mManager.PauseAndPlaySong();
    }

    public int getTotalSong() {
        return mManager.getTotalSong();
    }

    public int getCurrentSong() {
        return mManager.getCurrentSong();
    }

    public void updateSeekBar(int position) {
        mMediaPlayer.seekTo(position);
    }

    public class MyBinder extends Binder {
        public PlayerSongService getInstance() {
            return PlayerSongService.this;
        }
    }
}