package com.framgia.music_30.screen.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framgia.music_30.R;
import com.framgia.music_30.screen.home.fragment.GenreFragment;
import com.framgia.music_30.screen.home.fragment.ArtistFragment;
import com.framgia.music_30.screen.home.fragment.MySongsFragment;
import com.framgia.music_30.ultil.Constant;


public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private Context mContext;

    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constant.TAB_GENRE:
                return GenreFragment.newInstance();
            case Constant.TAB_MYSONG:
                return MySongsFragment.newInstance();
            case Constant.TAB_ARTIST:
                return ArtistFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Constant.TAB_TOTAL;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case Constant.TAB_GENRE:
                title = mContext.getString(R.string.GENRE_TITLE);
                break;
            case Constant.TAB_MYSONG:
                title = mContext.getString(R.string.MYSONGS_TITLE);
                break;
            case Constant.TAB_ARTIST:
                title = mContext.getString(R.string.ARTIST_TITLE);
                break;
        }
        return title;
    }
}
