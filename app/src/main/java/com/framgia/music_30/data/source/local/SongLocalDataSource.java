package com.framgia.music_30.data.source.local;

import android.util.Log;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.SongDataSource;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_30.ultil.Constant;

import java.io.File;
import java.util.ArrayList;

public class SongLocalDataSource implements SongDataSource.LocalDataSource {
    private static SongLocalDataSource sInstance;

    private SongLocalDataSource() {
    }

    public static SongLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SongLocalDataSource();
        }
        return sInstance;
    }

    @Override
    public void getData(OnFetchDataJsonListener<Song> listener) {
        ArrayList<Song> songs = getPlayList(Constant.FOLDER_OFFLINE_MUSIC);
        if (songs != null) {
            listener.onSucess(songs);
        } else {
            listener.onError(new Exception());
        }
    }

    public ArrayList<Song> getPlayList(String rootPath) {
        ArrayList<Song> songs = new ArrayList<>();
        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        songs.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(Constant.FILE_EXTEND)) {
                    Song song = new Song("", file.getName(), "", file.getAbsolutePath(), "", Constant.TYPE_OFFLINE);
                    songs.add(song);
                }
            }
            return songs;
        } catch (Exception e) {
            return null;
        }
    }
}
