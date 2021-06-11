package com.bkuhp2l.meowmusic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> arrayFragment = new ArrayList<>();
    ArrayList<String> arrayTitle = new ArrayList<>();

    public MainViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
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
    public void addFragment(Fragment fragment, String title) {
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }
    public String getTitle(int position) {
        return arrayTitle.get(position);
    }
}
