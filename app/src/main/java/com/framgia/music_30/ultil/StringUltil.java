package com.framgia.music_30.ultil;

public class StringUltil {
    public static StringBuilder getUrl(String url, String clientID, String apiKey) {
        StringBuilder builder = new StringBuilder();
        builder.append(url).append(clientID).append(apiKey);
        return builder;
    }
}
