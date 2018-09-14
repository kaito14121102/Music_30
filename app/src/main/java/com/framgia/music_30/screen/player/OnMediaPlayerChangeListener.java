package com.framgia.music_30.screen.player;

import com.framgia.music_30.data.model.Song;

public interface OnMediaPlayerChangeListener {
    void updateSong(Song song);

    void mediaError(Exception e);

    void updateShuffle(int typeShuffle);

    void updateLoop(int typeLoop);

    void updatePlayPause(int type);

    void updateTimeSong();

    void updateTimeTotal(int timeTotal);
}
