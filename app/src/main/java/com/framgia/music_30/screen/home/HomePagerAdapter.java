package com.framgia.music_30.screen.home;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framgia.music_30.screen.home.fragment.AlbumFragment;
import com.framgia.music_30.screen.home.fragment.ArtistFragment;
import com.framgia.music_30.screen.home.fragment.MySongsFragment;
import com.framgia.music_30.ultil.Constant;

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private AlbumFragment mAlbumFragment;
    private ArtistFragment mArtistFragment;
    private MySongsFragment mMySongsFragment;

    public HomePagerAdapter(FragmentManager fm, AlbumFragment albumFragment,
                            MySongsFragment mMySongsFragment, ArtistFragment artistFragment) {
        super(fm);
        this.mAlbumFragment = albumFragment;
        this.mMySongsFragment = mMySongsFragment;
        this.mArtistFragment = artistFragment;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mAlbumFragment;
            case 1:
                return mMySongsFragment;
            case 2:
                return mArtistFragment;
            default:
                return mAlbumFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = Constant.TITLE_FRAGMENT_ALBUM;
                break;
            case 1:
                title = Constant.TITLE_FRAGMENT_MY_SONGS;
                break;
            case 2:
                title = Constant.TITLE_FRAGMENT_ARTIST;
                break;
        }
        return title;
    }
}
