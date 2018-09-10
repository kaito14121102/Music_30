package com.framgia.music_30.screen.player;

import android.media.MediaPlayer;

import com.framgia.music_30.BuildConfig;
import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.ultil.APISoundCloud;
import com.framgia.music_30.ultil.Constant;
import com.framgia.music_30.ultil.StringUltil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PlayerManager implements MediaPlayer.OnCompletionListener {
    private ArrayList<Song> mSongs;
    private MediaPlayer mMediaPlayer;
    private OnMediaPlayerChangeListener mListener;
    private ServiceListener mServiceListener;
    private int mPosition;
    private int mShuffle;
    private int mLoop;

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

    public void setListener(OnMediaPlayerChangeListener listener) {
        mListener = listener;
    }

    public void setServiceListener(ServiceListener serviceListener) {
        mServiceListener = serviceListener;
    }

    public void playSong() {
        mServiceListener.updateNotification(mSongs.get(mPosition).getTitle());
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
            mMediaPlayer.reset();
            try {
                mMediaPlayer.setDataSource(StringUltil.
                        getUrl(mSongs.get(mPosition).getUrlPlay(), APISoundCloud.PLAY_CLIENT_ID, BuildConfig.API_KEY).toString());
                mMediaPlayer.prepare();
                mMediaPlayer.setOnCompletionListener(this);
                mMediaPlayer.start();
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
                mListener.updatePlayPause(R.drawable.ic_play_arrow_black_24dp);
            } else {
                mMediaPlayer.start();
                mListener.updatePlayPause(R.drawable.ic_pause_black_24dp);
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

    public void downloadSong() {
        mListener.downloadSong(StringUltil.getUrl(mSongs.get(mPosition).getUrlPlay(),
                APISoundCloud.PLAY_CLIENT_ID, BuildConfig.API_KEY).toString(),
                mSongs.get(mPosition).getTitle());
    }
}
