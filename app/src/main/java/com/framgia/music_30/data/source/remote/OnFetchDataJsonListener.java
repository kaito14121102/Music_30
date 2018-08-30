package com.framgia.music_30.data.source.remote;

import java.util.List;

public interface OnFetchDataJsonListener<T> {
    void onSucess(List<T> data);
}
