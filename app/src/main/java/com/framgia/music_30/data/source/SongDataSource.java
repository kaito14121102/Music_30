package com.framgia.music_30.data.source;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;

public interface SongDataSource {
    interface RemoteDataSource {
        void getData(OnFetchDataJsonListener<Song> listener);
    }
}
