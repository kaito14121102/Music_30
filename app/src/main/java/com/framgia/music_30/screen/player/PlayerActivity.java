package com.framgia.music_30.screen.player;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.music_30.R;

public class PlayerActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initWidget();
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
    }
}
