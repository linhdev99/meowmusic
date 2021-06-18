package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    public OnSongListener mOnSongListener;

    public SongPlaylistAdapter(Context mContext, ArrayList<Song> mSongs, OnSongListener onSongListener) {
        this.mContext = mContext;
        this.mSongs = mSongs;
        this.mOnSongListener = onSongListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View songPlaylistView = inflater.inflate(R.layout.line_playmusic_song, parent, false);
        ViewHolder viewHolder = new ViewHolder(songPlaylistView, mOnSongListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongPlaylistAdapter.ViewHolder holder, int position) {
        Song song = mSongs.get(position);
        holder.txtSong.setText(song.getNameSong());
        holder.txtSinger.setText(song.getNameSinger());
        holder.txtIndex.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtSong;
        TextView txtSinger;
        TextView txtIndex;
        ImageView btnLove;
        CardView cardView;

        OnSongListener onSongListener;

        public ViewHolder(View itemView, OnSongListener onSongListener) {
            super(itemView);
            txtSong = itemView.findViewById(R.id.textview_playmusic_song_namesong);
            txtSinger = itemView.findViewById(R.id.textview_playmusic_song_namesinger);
            txtIndex = itemView.findViewById(R.id.textview_playmusic_song_index);
            btnLove = itemView.findViewById(R.id.btn_playmusic_song_love);
            cardView = itemView.findViewById(R.id.btn_cardview_song_playlist);
            this.onSongListener = onSongListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSongListener.onSongClick(getBindingAdapterPosition());
        }
    }
    
    public interface OnSongListener {
        void onSongClick(int position);
    }
}
