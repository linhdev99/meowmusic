package com.bkuhp2l.meowmusic.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bkuhp2l.meowmusic.Adapter.BannerAdapter;
import com.bkuhp2l.meowmusic.Model.Banner;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.APIService;
import com.bkuhp2l.meowmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
//    ViewPager viewPager;
//    CircleIndicator circleIndicator;
    ViewPager2 viewPager;
    CircleIndicator3 circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentBanner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        Mapping();
        GetData();
        return view;
    }

    private void Mapping() {
        viewPager = view.findViewById(R.id.viewPagerBanner);
        circleIndicator = view.findViewById(R.id.indicatorDefault);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Banner>> callback = dataService.getDataBanner();
        callback.enqueue(new Callback<List<Banner>>() {

            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                ArrayList<Banner> banners = (ArrayList<Banner>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(), banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentBanner = viewPager.getCurrentItem();
                        currentBanner++;
                        if (currentBanner >= 3) {
                            currentBanner = 0;
                        }
                        viewPager.setCurrentItem(currentBanner, true);
                        handler.postDelayed(runnable, 7500);
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
        });
    }
}
