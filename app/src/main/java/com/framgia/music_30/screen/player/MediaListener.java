package com.framgia.music_30.screen.player;

import com.framgia.music_30.data.model.Song;

public interface MediaListener {
    void shuffle();

    void loop();

    void playSong();

    void nextSong();

    void previousSong();

    void pauseSong();

    int getCurrentSong();

    int getTotalSong();

    void updateSeekBar(int position);

    Song getSongCurrent();

    boolean isClickButtonPlay();
}
