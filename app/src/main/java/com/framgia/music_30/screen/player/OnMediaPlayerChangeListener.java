package com.framgia.music_30.screen.player;

import com.framgia.music_30.data.model.Song;

public interface OnMediaPlayerChangeListener {
    void updateSong(Song song);

    void mediaError(Exception e);

    void updateTimeSong();

    void updateTimeTotal(int timeTotal);
}
