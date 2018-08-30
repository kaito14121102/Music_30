package com.framgia.music_30.screen.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.framgia.music_30.R;
import com.framgia.music_30.screen.home.fragment.AlbumFragment;
import com.framgia.music_30.screen.home.fragment.ArtistFragment;
import com.framgia.music_30.screen.home.fragment.MySongsFragment;

public class HomeActivity extends AppCompatActivity {
    private ViewPager mPager;
    private TabLayout mTabLayout;
    private AlbumFragment mAlbumFragment;
    private MySongsFragment mMySongsFragment;
    private ArtistFragment mArtistFragment;
    private HomePagerAdapter mHomePagerAdapter;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initFragment();
        initViewPager();
        setUpTabIcon();
    }

    private void initWidget() {
        mPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

    }

    private void initFragment() {
        mAlbumFragment = new AlbumFragment();
        mMySongsFragment = new MySongsFragment();
        mArtistFragment = new ArtistFragment();
        mManager = getSupportFragmentManager();
    }

    private void initViewPager() {
        mHomePagerAdapter = new HomePagerAdapter(mManager, mAlbumFragment, mMySongsFragment, mArtistFragment);
        mPager.setAdapter(mHomePagerAdapter);
        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(mHomePagerAdapter);
    }

    private void setUpTabIcon() {
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_album_black_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_library_music_black_24dp);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_person_black_24dp);
    }
}

