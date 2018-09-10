package com.framgia.music_30.screen.player;

import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.music_30.BuildConfig;
import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.ultil.APISoundCloud;
import com.framgia.music_30.ultil.Constant;
import com.framgia.music_30.ultil.StringUltil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, OnMediaPlayerChangeListener {
    private static final String DATE_FORMAT = "mm:ss";
    private static final int UPDATE_DELAY = 500;
    private static final int HANDLER_DELAY = 100;
    private TextView mTextTitleSong;
    private TextView mTextDuration;
    private TextView mTextDurationToal;
    private ImageView mImageSong;
    private SeekBar mSeekBar;
    private ImageButton mBackWard;
    private ImageButton mForward;
    private ImageButton mPause;
    private ImageButton mBack;
    private ImageButton mDownload;
    private ImageButton mShuffle;
    private ImageButton mLoop;
    private PlayerSongService mSongService;
    private Boolean mIsBound;
    private MediaListener mMediaListener;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayerSongService.MyBinder binder = (PlayerSongService.MyBinder) iBinder;
            mSongService = binder.getInstance();
            mSongService.setListener(PlayerActivity.this);
            mMediaListener = mSongService.newInstance();
            updateSong(mSongService.getSongCurrent());
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIsBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initWidget();
        bindService(PlayerSongService.getIntentBindService(this), mConnection, PlayerActivity.BIND_AUTO_CREATE);
        evenSeekBar();
    }

    private void evenSeekBar() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mMediaListener.updateSeekBar(seekBar.getProgress());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_button_pause:
                mMediaListener.pauseSong();
                break;
            case R.id.image_button_forward:
                mMediaListener.nextSong();
                break;
            case R.id.image_button_backward:
                mMediaListener.previousSong();
                break;
            case R.id.image_button_back:
                finish();
                break;
            case R.id.image_button_shuffle:
                mMediaListener.shuffle();
                break;
            case R.id.image_button_loop:
                mMediaListener.loop();
            case R.id.image_button_download:
                mMediaListener.downLoadSong();
                break;
            default:
                break;
        }
    }

    @Override
    public void updateSong(Song song) {
        mTextTitleSong.setText(song.getTitle());
        Picasso.with(this)
                .load(song.getImageSong())
                .into(mImageSong);
        UpdateTimeSong();
    }

    @Override
    public void mediaError(Exception e) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateShuffle(int typeShuffle) {
        mShuffle.setImageResource(typeShuffle);
    }

    @Override
    public void updateLoop(int typeLoop) {
        mLoop.setImageResource(typeLoop);
    }

    @Override
    public void updatePlayPause(int type) {
        mPause.setImageResource(type);
    }

    @Override
    public void downloadSong(String url, String name) {
        new SongDownload(this, name).execute(url);
        mDownload.setImageResource(R.drawable.ic_arrow_downloaded_black_24dp);
    }

    private void setTimeTotal() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        mTextDurationToal.setText(dateFormat.format(mSongService.getTotalSong()));
        mSeekBar.setMax(mMediaListener.getTotalSong());
    }

    private void UpdateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
                mTextDuration.setText(simpleDateFormat.format(mMediaListener.getCurrentSong()));
                setTimeTotal();
                mSeekBar.setProgress(mMediaListener.getCurrentSong());
                handler.postDelayed(this, UPDATE_DELAY);
            }
        }, HANDLER_DELAY);
    }


    private void initWidget() {
        mTextTitleSong = findViewById(R.id.text_title_song);
        mTextDuration = findViewById(R.id.text_duration);
        mTextDurationToal = findViewById(R.id.text_duration_total);
        mImageSong = findViewById(R.id.image_song);
        mSeekBar = findViewById(R.id.seek_bar);
        mBackWard = findViewById(R.id.image_button_backward);
        mForward = findViewById(R.id.image_button_forward);
        mPause = findViewById(R.id.image_button_pause);
        mLoop = findViewById(R.id.image_button_loop);
        mShuffle = findViewById(R.id.image_button_shuffle);
        mBack = findViewById(R.id.image_button_back);
        mDownload = findViewById(R.id.image_button_download);
        setEvenButton();
    }

    private void setEvenButton() {
        mBackWard.setOnClickListener(this);
        mForward.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mLoop.setOnClickListener(this);
        mShuffle.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mDownload.setOnClickListener(this);
    }
}