package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Activity.AdvertisementActivity;
import com.bkuhp2l.meowmusic.Model.Banner;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    Context context;
    ArrayList<Banner> mBanners;

    public BannerAdapter(Context context, ArrayList<Banner> mBanners) {
        this.context = context;
        this.mBanners = mBanners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View bannerView = inflater.inflate(R.layout.line_banner, parent, false);
        ViewHolder viewHolder = new ViewHolder(bannerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.ViewHolder holder, int position) {
        Banner banner = mBanners.get(position);
        Picasso.with(context).load(mBanners.get(position).getImage()).into(holder.imgBackgroundBanner);
        Picasso.with(context).load(mBanners.get(position).getAvatar()).into(holder.imgIconBanner);
        holder.txtTitleSongBanner.setText(mBanners.get(position).getTitle());
        holder.txtContentBanner.setText(mBanners.get(position).getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdvertisementActivity.class);
                intent.putExtra("banner", mBanners.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBanners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBackgroundBanner, imgIconBanner;
        TextView txtTitleSongBanner, txtContentBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBackgroundBanner = itemView.findViewById(R.id.imageViewBackgroundBanner);
            imgIconBanner = itemView.findViewById(R.id.imageViewBanner);
            txtTitleSongBanner = itemView.findViewById(R.id.textViewTitleBanner);
            txtContentBanner = itemView.findViewById(R.id.textViewContentBanner);
        }
    }
}

//    Context context;
//    ArrayList<Banner> arrayBanner;
//
//    public BannerAdapter(Context context, ArrayList<Banner> arrayBanner) {
//        this.context = context;
//        this.arrayBanner = arrayBanner;
//    }
//
//    @Override
//    public int getCount() {
//        return arrayBanner.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.line_banner, null);
//        ImageView imgBackgroundBanner = view.findViewById(R.id.imageViewBackgroundBanner);
//        ImageView imgIconBanner = view.findViewById(R.id.imageViewBanner);
//        TextView txtTitleSongBanner = view.findViewById(R.id.textViewTitleBanner);
//        TextView txtContentBanner = view.findViewById(R.id.textViewContentBanner);
//
//        Picasso.with(context).load(arrayBanner.get(position).getImage()).into(imgBackgroundBanner);
//        Picasso.with(context).load(arrayBanner.get(position).getAvatar()).into(imgIconBanner);
//        txtTitleSongBanner.setText(arrayBanner.get(position).getTitle());
//        txtContentBanner.setText(arrayBanner.get(position).getContent());
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(context, "Click Banner " + arrayBanner.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, AdvertisementActivity.class);
//                intent.putExtra("banner", arrayBanner.get(position));
//                context.startActivity(intent);
//            }
//        });
////        imgIconBanner.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Toast.makeText(context, "Click Banner " + arrayBanner.get(position).getTitle(), Toast.LENGTH_SHORT).show();
////            }
////        });
//
//        container.addView(view);
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
//}
