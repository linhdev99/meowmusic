package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Model.Album;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<Album> mAlbums;

    public AlbumAdapter(Context mContext, ArrayList<Album> mAlbums) {
        this.mContext = mContext;
        this.mAlbums = mAlbums;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View topicView = inflater.inflate(R.layout.line_album, parent, false);
        ViewHolder viewHolder = new ViewHolder(topicView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        Album topic = mAlbums.get(position);
        Picasso.with(mContext).load(topic.getImgAlbum()).into(holder.imgBackground);
        holder.txtAlbum.setText(topic.getNameAlbum());
        holder.txtSinger.setText(topic.getNameSinger());
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAlbum;
        TextView txtSinger;
        ImageView imgBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            txtAlbum = itemView.findViewById(R.id.textViewTitleAlbum);
            txtSinger = itemView.findViewById(R.id.textViewNameSingerAlbum);
            imgBackground = itemView.findViewById(R.id.imgBackgroundAlbum);
        }
    }
}
