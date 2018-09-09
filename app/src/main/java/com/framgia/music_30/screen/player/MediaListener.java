package com.framgia.music_30.screen.player;

public interface MediaListener {
    void shuffle();

    void loop();

    void playSong();

    void nextSong();

    void previousSong();

    void pauseSong();

    int getTotalSong();

    int getCurrentSong();

    void updateSeekBar(int position);
}
