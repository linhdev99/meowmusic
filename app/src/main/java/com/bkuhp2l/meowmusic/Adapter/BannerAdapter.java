package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bkuhp2l.meowmusic.Model.Banner;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Banner> arrayBanner;

    public BannerAdapter(Context context, ArrayList<Banner> arrayBanner) {
        this.context = context;
        this.arrayBanner = arrayBanner;
    }

    @Override
    public int getCount() {
        return arrayBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_banner, null);
        ImageView imgBackgroundBanner = view.findViewById(R.id.imageViewBackgroundBanner);
        ImageView imgSongBanner = view.findViewById(R.id.imageViewBanner);
        TextView txtTitleSongBanner = view.findViewById(R.id.textViewTitleBanner);
        TextView txtContentBanner = view.findViewById(R.id.textViewContentBanner);

        Picasso.with(context).load(arrayBanner.get(position).getImage()).into(imgBackgroundBanner);
        Picasso.with(context).load(arrayBanner.get(position).getImageSong()).into(imgSongBanner);
        txtTitleSongBanner.setText(arrayBanner.get(position).getNameSong());
        txtContentBanner.setText(arrayBanner.get(position).getContent());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
