package com.framgia.music_30.screen.player;

public interface MediaListener {
    void playSong();

    void nextSong();

    void previousSong();

    void pauseSong();

    int getCurrentSong();

    void updateSeekBar(int position);
}
