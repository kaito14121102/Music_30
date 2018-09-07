package com.framgia.music_30.screen.player;

import android.media.MediaPlayer;

import com.framgia.music_30.BuildConfig;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.ultil.APISoundCloud;
import com.framgia.music_30.ultil.StringUltil;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerManager {
    private ArrayList<Song> mSongs;
    private MediaPlayer mMediaPlayer;
    private UpdateUiPlayerListener mUpdateUiListener;
    private int mPosition;

    public PlayerManager(int position, ArrayList<Song> songs, MediaPlayer media, UpdateUiPlayerListener listener) {
        if (songs != null) {
            mPosition = position;
            mSongs = songs;
            mMediaPlayer = media;
            mUpdateUiListener = listener;
        }
    }

    public void playSong() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
            mMediaPlayer.reset();
            try {
                mMediaPlayer.setDataSource(StringUltil.
                        getUrl(mSongs.get(mPosition).getUrlPlay(), APISoundCloud.PLAY_CLIENT_ID, BuildConfig.API_KEY).toString());
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                mUpdateUiListener.updateSong(mSongs.get(mPosition).getTitle(), mSongs.get(mPosition).getImageSong());
            } catch (IOException e) {
                mUpdateUiListener.mediaError(e);
            }
        }
    }

    public void nextSong() {
        if (mPosition == mSongs.size() - 1) {
            mPosition = 0;
        } else mPosition++;
        playSong();
    }

    public void previousSong() {
        if (mPosition == 0) {
            mPosition = mSongs.size() - 1;
        } else {
            mPosition--;
        }
        playSong();
    }

    public int getTotalSong() {
        return mMediaPlayer.getDuration();
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
