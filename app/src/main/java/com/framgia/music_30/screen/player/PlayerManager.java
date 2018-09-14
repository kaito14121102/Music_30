package com.framgia.music_30.screen.player;

import android.media.MediaPlayer;

import com.framgia.music_30.BuildConfig;
import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.screen.player.OnMediaPlayerChangeListener;
import com.framgia.music_30.ultil.APISoundCloud;
import com.framgia.music_30.ultil.Constant;
import com.framgia.music_30.ultil.StringUltil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PlayerManager implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {
    private ArrayList<Song> mSongs;
    private MediaPlayer mMediaPlayer;
    private OnMediaPlayerChangeListener mListener;
    private ServiceListener mServiceListener;
    private int mPosition;
    private int mShuffle;
    private int mLoop;
    private boolean mIsUpdateUi;

    public PlayerManager(int position, ArrayList<Song> songs, MediaPlayer media) {
        if (songs != null) {
            mPosition = position;
            mSongs = songs;
            mMediaPlayer = media;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mShuffle == Constant.NONE_SHUFFLE) {
            if (mLoop == Constant.LOOP_ALL) {
                nextSong();
            } else if (mLoop == Constant.LOOP_ONE) {
                playSong();
            }
        } else {
            if (mLoop == Constant.LOOP_ALL) {
                mPosition = new Random().nextInt(mSongs.size() - 1);
                mListener.updateSong(mSongs.get(mPosition));
                playSong();
            } else if (mLoop == Constant.LOOP_ONE) {
                playSong();
            }
        }
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

    public void setServiceListener(ServiceListener serviceListener) {
        mServiceListener = serviceListener;
    }

    public void playSong() {
        mServiceListener.updateNotification(mSongs.get(mPosition).getTitle(), R.drawable.ic_pause_black_24dp);
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
                    mIsUpdateUi = true;
                }
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnPreparedListener(this);
                mMediaPlayer.setOnBufferingUpdateListener(this);
                mMediaPlayer.setOnCompletionListener(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void nextSong() {
        mIsUpdateUi = false;
        if (mShuffle == Constant.SHUFFLE) {
            mPosition = new Random().nextInt(mSongs.size() - 1);
            mListener.updateSong(mSongs.get(mPosition));
            playSong();
        } else {
            if (mPosition == mSongs.size() - 1) {
                mPosition = 0;
            } else {
                mPosition++;
            }
            mListener.updateSong(mSongs.get(mPosition));
            playSong();
        }
    }

    public Boolean isUpdateUI() {
        return mIsUpdateUi;
    }

    public void previousSong() {
        mIsUpdateUi = false;
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

    public int getTotalSong() {
        return mMediaPlayer.getDuration();
    }

    public Song getSong() {
        return mSongs.get(mPosition);
    }

    public void PauseAndPlaySong() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                mListener.updatePlayPause(R.drawable.ic_play_arrow_black_24dp);
                mServiceListener.updateNotification(mSongs.get(mPosition).getTitle(), R.drawable.ic_play_arrow_black_24dp);
            } else {
                mMediaPlayer.start();
                mListener.updatePlayPause(R.drawable.ic_pause_black_24dp);
                mServiceListener.updateNotification(mSongs.get(mPosition).getTitle(), R.drawable.ic_pause_black_24dp);
            }
        }
    }

    public void shuffle() {
        switch (mShuffle) {
            case Constant.NONE_SHUFFLE:
                mShuffle = Constant.SHUFFLE;
                mListener.updateShuffle(R.drawable.ic_shuffle_black_24dp);
                break;
            case Constant.SHUFFLE:
                mShuffle = Constant.NONE_SHUFFLE;
                mListener.updateShuffle(R.drawable.ic_none_shuffle_black_24dp);
                break;
        }
    }

    public void loop() {
        switch (mLoop) {
            case Constant.LOOP_ALL:
                mLoop = Constant.LOOP_ONE;
                mListener.updateLoop(R.drawable.ic_repeat_one_black_24dp);
                break;
            case Constant.LOOP_ONE:
                mLoop = Constant.NONE_LOOP;
                mListener.updateLoop(R.drawable.ic_none_loop_black_24dp);
                break;
            case Constant.NONE_LOOP:
                mLoop = Constant.LOOP_ALL;
                mListener.updateLoop(R.drawable.ic_loop_black_24dp);
                break;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (i > 0) {
            mIsUpdateUi = true;
        }
    }
}
