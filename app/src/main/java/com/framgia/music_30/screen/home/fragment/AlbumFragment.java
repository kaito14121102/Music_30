package com.framgia.music_30.screen.home.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.music_30.R;

public class AlbumFragment extends Fragment {

    public static AlbumFragment newInstance() {
        AlbumFragment albumFragment = new AlbumFragment();
        return albumFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_album, container, false);
        return mView;
    }
}
