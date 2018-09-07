package com.framgia.music_30.screen.player;

import android.media.MediaPlayer;

import com.framgia.music_30.BuildConfig;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.ultil.APISoundCloud;
import com.framgia.music_30.ultil.Constant;
import com.framgia.music_30.ultil.StringUltil;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerManager implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private ArrayList<Song> mSongs;
    private MediaPlayer mMediaPlayer;
    private OnMediaPlayerChangeListener mListener;
    private int mPosition;


    public PlayerManager(int position, ArrayList<Song> songs, MediaPlayer media) {
        if (songs != null) {
            mPosition = position;
            mSongs = songs;
            mMediaPlayer = media;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        if (mListener != null) {
            mListener.updateTimeTotal(mMediaPlayer.getDuration());
            mListener.updateTimeSong();
        }
    }

    public void setListener(OnMediaPlayerChangeListener listener) {
        mListener = listener;
    }

    public void playSong() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
            mMediaPlayer.reset();
            try {
                if (mSongs.get(mPosition).getType().equals(Constant.TYPE_ONLINE)) {
                    mMediaPlayer.setDataSource(StringUltil.
                            getUrl(mSongs.get(mPosition).getUrlPlay(), APISoundCloud.PLAY_CLIENT_ID, BuildConfig.API_KEY).toString());
                } else {
                    mMediaPlayer.setDataSource(mSongs.get(mPosition).getUrlPlay());
                }
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnPreparedListener(this);
                mMediaPlayer.setOnCompletionListener(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void nextSong() {
        if (mPosition == mSongs.size() - 1) {
            mPosition = 0;
        } else mPosition++;
        mListener.updateSong(mSongs.get(mPosition));
        playSong();
    }

    public void previousSong() {
        if (mPosition == 0) {
            mPosition = mSongs.size() - 1;
        } else {
            mPosition--;
        }
        mListener.updateSong(mSongs.get(mPosition));
        playSong();
    }

    public int getCurrentSong() {
        return mMediaPlayer.getCurrentPosition();
    }

    public void PauseAndPlaySong() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            } else {
                mMediaPlayer.start();
            }
        }
    }
}
