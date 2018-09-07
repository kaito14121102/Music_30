package com.framgia.music_30.screen.player;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;

import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.ultil.Constant;
import com.squareup.picasso.Picasso;

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
    private Handler mHandler;
    private boolean mIsDownloaded;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayerSongService.MyBinder binder = (PlayerSongService.MyBinder) iBinder;
            mSongService = binder.getInstance();
            mSongService.setListener(PlayerActivity.this);
            mMediaListener = mSongService.newInstance();
            updateSong(mMediaListener.getSongCurrent());
            updateTimeSong();
            updateTimeTotal(mMediaListener.getTotalSong());
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
        evenSeekBar();
    }

    @Override
    protected void onStart() {
        bindService(PlayerSongService.getIntentBindService(this), mConnection, PlayerActivity.BIND_AUTO_CREATE);
        super.onStart();
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
                if (mMediaListener.isClickButtonPlay())
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
                break;
            case R.id.image_button_download:
                if (mIsDownloaded == false)
                    mMediaListener.downLoadSong();
                break;
            default:
                break;
        }
    }

    @Override
    public void updateSong(Song song) {
        mTextTitleSong.setText(song.getTitle());
        if (song.getType().equals(Constant.TYPE_ONLINE)) {
            Picasso.with(this)
                    .load(song.getImageSong())
                    .error(R.drawable.zing)
                    .into(mImageSong);
        } else {
            mImageSong.setImageResource(R.drawable.musicplay);
            mDownload.setImageResource(R.drawable.ic_arrow_downloaded_black_24dp);
        }
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
    public void updateTimeTotal(int timetotal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        mTextDurationToal.setText(dateFormat.format(timetotal));
        mSeekBar.setMax(timetotal);
    }

    @Override
    public void downloadSong(String url, String name) {
        new SongDownload(this, name).execute(url);
        mDownload.setImageResource(R.drawable.ic_arrow_downloaded_black_24dp);
        mIsDownloaded = true;
    }

    @Override
    public void updateTimeSong() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
                mTextDuration.setText(simpleDateFormat.format(mMediaListener.getCurrentSong()));
                mSeekBar.setProgress(mMediaListener.getCurrentSong());
                mHandler.postDelayed(this, UPDATE_DELAY);
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
