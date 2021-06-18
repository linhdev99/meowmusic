package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Activity.PlayMusicActivity;
import com.bkuhp2l.meowmusic.Model.Song;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotMusicAdapter extends RecyclerView.Adapter<HotMusicAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<Song> mSongs;

    public HotMusicAdapter(Context mContext, ArrayList<Song> mSongs) {
        this.mContext = mContext;
        this.mSongs = mSongs;
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
        Song topic = mSongs.get(position);
        Picasso.with(mContext).load(topic.getImgSong()).into(holder.imgAvatar);
        holder.txtSong.setText(topic.getNameSong());
        holder.txtSinger.setText(topic.getNameSinger());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, mHotMusics.get(position).getNameSong(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, PlayMusicActivity.class);
                intent.putExtra("hotmusic", mSongs.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSong;
        TextView txtSinger;
        ImageView imgAvatar;
        ImageView iconBtnLove;
        CardView btnLineHotmusic;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSong = itemView.findViewById(R.id.textViewNameSongHotMusic);
            txtSinger = itemView.findViewById(R.id.textViewNameSingerHotMusic);
            imgAvatar = itemView.findViewById(R.id.imgAvatarSongHotMusic);
            iconBtnLove = itemView.findViewById(R.id.imgBtnLoveSongHotMusic);
            btnLineHotmusic = itemView.findViewById(R.id.btn_line_hotmusic);
        }
    }
}
