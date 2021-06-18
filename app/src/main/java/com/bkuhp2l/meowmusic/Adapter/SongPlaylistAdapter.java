package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Activity.PlayMusicActivity;
import com.bkuhp2l.meowmusic.Model.Song;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongPlaylistAdapter extends RecyclerView.Adapter<SongPlaylistAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<Song> mSongs;
    public int id_current_play;

    public SongPlaylistAdapter(Context mContext, ArrayList<Song> mSongs, int id_current_play) {
        this.mContext = mContext;
        this.mSongs = mSongs;
        this.id_current_play = id_current_play;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View topicView = inflater.inflate(R.layout.line_playmusic_song, parent, false);
        ViewHolder viewHolder = new ViewHolder(topicView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongPlaylistAdapter.ViewHolder holder, int position) {
        Song song = mSongs.get(position);
        holder.txtSong.setText(song.getNameSong());
        holder.txtSinger.setText(song.getNameSinger());
        holder.txtIndex.setText(String.valueOf(position+1));
        if (position == this.id_current_play) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.choose_song_play));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.notchoose_song_play));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Click", Toast.LENGTH_SHORT).show();
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
        TextView txtIndex;
        ImageView btnLove;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSong = itemView.findViewById(R.id.textview_playmusic_song_namesong);
            txtSinger = itemView.findViewById(R.id.textview_playmusic_song_namesinger);
            txtIndex = itemView.findViewById(R.id.textview_playmusic_song_index);
            btnLove = itemView.findViewById(R.id.btn_playmusic_song_love);
            cardView = itemView.findViewById(R.id.btn_cardview_song_playlist);
        }
    }
}
