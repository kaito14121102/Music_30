package com.framgia.music_30.screen.player;

public interface OnMediaPlayerChangeListener {
    void updateSong(String nameSong, String imageSong);

    void mediaError(Exception e);

    void updateShuffle(int typeShuffle);

    void updateLoop(int typeLoop);

    void updatePlayPause(int type);
}
