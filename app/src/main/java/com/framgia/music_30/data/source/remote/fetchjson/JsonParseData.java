package com.framgia.music_30.data.source.remote.fetchjson;

import android.util.Log;

import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.ultil.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonParseData {
    private static final int TIME_OUT = 15000;
    private static final String METHOD_GET = "GET";

    public String getJsonFromUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(TIME_OUT);
        httpURLConnection.setReadTimeout(TIME_OUT);
        httpURLConnection.setRequestMethod(METHOD_GET);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        httpURLConnection.disconnect();
        return stringBuilder.toString();
    }

    public List<Song> parseJsonToSongGenre(String s) throws JSONException {
        List<Song> songList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(s);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject songJson = jsonArray.getJSONObject(i);
            String id = songJson.getString(Constant.ID_SONG);
            String title = songJson.getString(Constant.TITLE_SONG);
            String urlPlay = songJson.getString(Constant.STREAM_URL);
            String urlDownload = songJson.getString(Constant.DOWNLOAD_URL);
            String imageSong = songJson.getString(Constant.IMAGE_SONG);
            songList.add(new Song(id, title, imageSong, urlPlay, urlDownload, Constant.TYPE_ONLINE));
        }
        return songList;
    }

    public List<Song> parseJsonToSongSearch(String s) throws JSONException {
        List<Song> songList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(s);
        JSONArray jsonArray = jsonObject.getJSONArray("collection");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject songJson = jsonArray.getJSONObject(i);
            String id = songJson.getString(Constant.ID_SONG);
            String title = songJson.getString(Constant.TITLE_SONG);
            String urlPlay = songJson.getString(Constant.STREAM_URL);
            String urlDownload = songJson.getString(Constant.DOWNLOAD_URL);
            String imageSong = songJson.getString(Constant.IMAGE_SONG);
            songList.add(new Song(id, title, imageSong, urlPlay, urlDownload, Constant.TYPE_ONLINE));
        }
        return songList;
    }
}
