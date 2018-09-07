package com.framgia.music_30.screen.player;

public interface UpdateUiPlayerListener {
    void updateSong(String nameSong, String imageSong);
    void mediaError(Exception e);
}
