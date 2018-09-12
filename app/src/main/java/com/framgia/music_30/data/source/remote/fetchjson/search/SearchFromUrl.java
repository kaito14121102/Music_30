package com.framgia.music_30.data.source.remote.fetchjson.search;

import android.os.AsyncTask;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.data.source.remote.OnFetchDataJsonListener;
import com.framgia.music_30.data.source.remote.fetchjson.JsonParseData;

import java.util.ArrayList;

public class SearchFromUrl extends AsyncTask<String, Void, ArrayList<Song>> {
    private OnFetchDataJsonListener<Song> mListener;
    private Exception mException;

    public SearchFromUrl(OnFetchDataJsonListener<Song> listener) {
        mListener = listener;
    }

    @Override
    protected ArrayList<Song> doInBackground(String... strings) {
        ArrayList<Song> songs = null;
        String data = "";
        JsonParseData jsonParseData = new JsonParseData();
        try {
            data = jsonParseData.getJsonFromUrl(strings[0]);
            songs = (ArrayList<Song>) new JsonParseData().parseJsonToSongSearch(data);
        } catch (Exception e) {
            mException = e;
        }
        return songs;
    }

    @Override
    protected void onPostExecute(ArrayList<Song> songArrayList) {
        super.onPostExecute(songArrayList);
        if (mException == null) {
            mListener.onSucess(songArrayList);
        } else {
            mListener.onError(mException);
        }
    }
}