package com.framgia.music_30.screen.player;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.screen.songgenre.SongGenreActivity;
import com.framgia.music_30.ultil.Constant;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerSongService extends Service implements MediaListener, ServiceListener {
    private ArrayList<Song> mSongs;
    private int mPosition = 0;
    private PlayerManager mManager;
    private MediaPlayer mMediaPlayer;
    public static final int REQUEST_CODE_NOTIFICATION = 123;
    public static final int NOTIFICATION_ID = 1337;
    private OnMediaPlayerChangeListener mListener;

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
        BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case Constant.NOTIFICATION_BACK_EVENT:
                        previousSong();
                        break;
                    case Constant.NOTIFICATION_NEXT_EVENT:
                        nextSong();
                        break;
                    case Constant.NOTIFICATION_PAUSE_EVENT:
                        pauseSong();
                        break;
                }
            }
        };
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Constant.NOTIFICATION_BACK_EVENT);
        mIntentFilter.addAction(Constant.NOTIFICATION_NEXT_EVENT);
        mIntentFilter.addAction(Constant.NOTIFICATION_PAUSE_EVENT);
        registerReceiver(mBroadcastReceiver, mIntentFilter);

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
        mManager = new PlayerManager(mPosition, mSongs, mMediaPlayer);
        mManager.setServiceListener(this);
        mManager.playSong();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void shuffle() {
        mManager.shuffle();
    }

    @Override
    public void loop() {
        mManager.loop();
    }

    @Override
    public void playSong() {
        mManager.playSong();
    }

    @Override
    public void nextSong() {
        mManager.nextSong();
    }

    @Override
    public void previousSong() {
        mManager.previousSong();
    }

    @Override

    public void pauseSong() {
        mManager.PauseAndPlaySong();
    }

    @Override

    public int getTotalSong() {
        return mManager.getTotalSong();
    }

    @Override

    public int getCurrentSong() {
        return mManager.getCurrentSong();
    }


    public void updateSeekBar(int position) {
        mMediaPlayer.seekTo(position);
    }

    @Override
    public void updateNotification(String titleSong) {
        notification(titleSong);
    }

    public Song getSongCurrent() {
        return mSongs.get(mPosition);
    }

    public void setListener(OnMediaPlayerChangeListener listener) {
        mListener = listener;
        mManager.setListener(mListener);
    }

    public MediaListener newInstance() {
        return this;
    }

    public void notification(String titleSong) {
        Intent notificationIntent = new Intent(this, PlayerActivity.class);
        Intent intentBack = new Intent(Constant.NOTIFICATION_BACK_EVENT);
        Intent intentPause = new Intent(Constant.NOTIFICATION_PAUSE_EVENT);
        Intent intentNext = new Intent(Constant.NOTIFICATION_NEXT_EVENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        PendingIntent pendingIntentBack = PendingIntent.getBroadcast(getApplication(), REQUEST_CODE_NOTIFICATION, intentBack, 0);
        PendingIntent pendingIntentPause = PendingIntent.getBroadcast(getApplication(), REQUEST_CODE_NOTIFICATION, intentPause, 0);
        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(getApplication(), REQUEST_CODE_NOTIFICATION, intentNext, 0);

        Notification notification =
                new NotificationCompat.Builder(this, "a")
                        .setContentTitle(titleSong)
                        .setSmallIcon(R.drawable.zing)
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.ic_skip_previous_black_24dp, "", pendingIntentBack)
                        .addAction(R.drawable.ic_pause_black_24dp, "", pendingIntentPause)
                        .addAction(R.drawable.ic_skip_next_black_24dp, "", pendingIntentNext)
                        .build();
        startForeground(NOTIFICATION_ID, notification);
    }

    public class MyBinder extends Binder {
        public PlayerSongService getInstance() {
            return PlayerSongService.this;
        }
    }
}
