package com.bkuhp2l.meowmusic.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.StrictMode;

import com.bkuhp2l.meowmusic.Adapter.MainViewPagerAdapter;
import com.bkuhp2l.meowmusic.Fragment.Fragment_Home;
import com.bkuhp2l.meowmusic.Fragment.Fragment_PlayMusic;
import com.bkuhp2l.meowmusic.Fragment.Fragment_Search;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.PlayMusicService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actionBar = getSupportActionBar();
        actionBar.hide();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }
    private void init() {
        getSupportActionBar().setElevation(0);
        PlayMusicService.getInstance();
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(this);
        mainViewPagerAdapter.addFragment(new Fragment_Home(), "Home");
        mainViewPagerAdapter.addFragment(new Fragment_Search(), "Search");
//        mainViewPagerAdapter.addFragment(new Fragment_PlayMusic(), "Play Music");
        viewPager2.setAdapter(mainViewPagerAdapter);
//        new TabLayoutMediator(tabLayout, viewPager2,
//                (tab, position) -> tab.setText(mainViewPagerAdapter.getTitle(position))
//        ).attach();
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText("")).attach();
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
//        tabLayout.getTabAt(2).setIcon(R.drawable.playmusiclayout);

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();
    }
    private void mapping() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager2 = findViewById(R.id.myViewPager);
    }
}