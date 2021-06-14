package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Model.HotMusic;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotMusicAdapter extends RecyclerView.Adapter<HotMusicAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<HotMusic> mHotMusics;

    public HotMusicAdapter(Context mContext, ArrayList<HotMusic> mHotMusics) {
        this.mContext = mContext;
        this.mHotMusics = mHotMusics;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View topicView = inflater.inflate(R.layout.line_hotmusic, parent, false);
        ViewHolder viewHolder = new ViewHolder(topicView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotMusicAdapter.ViewHolder holder, int position) {
        HotMusic topic = mHotMusics.get(position);
        Picasso.with(mContext).load(topic.getImgSong()).into(holder.imgAvatar);
        holder.txtSong.setText(topic.getNameSong());
        holder.txtSinger.setText(topic.getNameSinger());
    }

    @Override
    public int getItemCount() {
        return mHotMusics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSong;
        TextView txtSinger;
        ImageView imgAvatar;
        ImageView iconBtnLove;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSong = itemView.findViewById(R.id.textViewNameSongHotMusic);
            txtSinger = itemView.findViewById(R.id.textViewNameSingerHotMusic);
            imgAvatar = itemView.findViewById(R.id.imgAvatarSongHotMusic);
            iconBtnLove = itemView.findViewById(R.id.imgBtnLoveSongHotMusic);
        }
    }
}
