package com.bkuhp2l.meowmusic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class PlayMusicPagerAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> arrayFragment = new ArrayList<>();
    public PlayMusicPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayFragment.size();
    }
    public void addFragment(Fragment fragment) {
        arrayFragment.add(fragment);
    }

}
