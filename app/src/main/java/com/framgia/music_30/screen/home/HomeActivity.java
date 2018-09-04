package com.framgia.music_30.screen.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.framgia.music_30.R;
import com.framgia.music_30.ultil.Constant;

public class HomeActivity extends AppCompatActivity {
    private ViewPager mPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initViewPager();
        setUpTabIcon();
    }

    private void initWidget() {
        mPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);
    }

    private void initViewPager() {
        FragmentManager mManager = getSupportFragmentManager();
        HomePagerAdapter mHomePagerAdapter = new HomePagerAdapter(mManager, this);
        mPager.setAdapter(mHomePagerAdapter);
        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(mHomePagerAdapter);
    }

    private void setUpTabIcon() {
        mTabLayout.getTabAt(Constant.TAB_GENRE).setIcon(R.drawable.ic_album_black_24dp);
        mTabLayout.getTabAt(Constant.TAB_MYSONG).setIcon(R.drawable.ic_library_music_black_24dp);
        mTabLayout.getTabAt(Constant.TAB_ARTIST).setIcon(R.drawable.ic_person_black_24dp);
    }
}
