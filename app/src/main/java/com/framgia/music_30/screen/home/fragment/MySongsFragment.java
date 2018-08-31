package com.framgia.music_30.screen.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.music_30.R;

public class MySongsFragment extends Fragment {

    public static MySongsFragment newInstance() {
        MySongsFragment mySongsFragment = new MySongsFragment();
        return mySongsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_mysongs, container, false);
        return mView;
    }
}
