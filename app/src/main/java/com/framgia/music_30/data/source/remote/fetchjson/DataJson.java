package com.framgia.music_30.data.source.remote.fetchjson;

import com.framgia.music_30.BuildConfig;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_30.data.source.remote.fetchjson.search.SearchFromUrl;
import com.framgia.music_30.ultil.APISoundCloud;

public class DataJson {
    private OnFetchDataJsonListener<Song> mListener;

    public DataJson(OnFetchDataJsonListener<Song> listener) {
        mListener = listener;
    }

    public void getData(String genre) {
        String url = APISoundCloud.BASE_URL
                + genre
                + APISoundCloud.CLIENT_ID
                + BuildConfig.API_KEY;
        new JsonFromUrl(mListener).execute(url);
    }

    public void getDataSearch(String textSearch) {
        String url = APISoundCloud.SEARCH_URL
                + APISoundCloud.PLAY_CLIENT_ID
                + BuildConfig.API_KEY
                + APISoundCloud.QUERY_SEARCH
                + textSearch;
        new SearchFromUrl(mListener).execute(url);
    }
}
