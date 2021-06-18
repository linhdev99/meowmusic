package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Model.Playlist;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MorePlaylistAdapter extends RecyclerView.Adapter<MorePlaylistAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<Playlist> mMorePlaylists;
    public MorePlaylistAdapter(Context mContext, ArrayList<Playlist> mMorePlaylists) {
        this.mContext = mContext;
        this.mMorePlaylists = mMorePlaylists;
    }
    @NonNull
    @Override
    public MorePlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View morePlaylistView = inflater.inflate(R.layout.item_more_playlist, parent, false);
        MorePlaylistAdapter.ViewHolder viewHolder = new MorePlaylistAdapter.ViewHolder(morePlaylistView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MorePlaylistAdapter.ViewHolder holder, int position) {
        Playlist playlist = mMorePlaylists.get(position);
        Picasso.with(mContext).load(playlist.getIcon()).into(holder.imgBackground);
        holder.txtTitle.setText(playlist.getName());
        holder.relativeLayout.setGravity(RelativeLayout.CENTER_VERTICAL);
    }

    @Override
    public int getItemCount() {
        return mMorePlaylists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBackground;
        TextView txtTitle;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBackground = (ImageView) itemView.findViewById(R.id.img_item_more_playlist_avatar);
            txtTitle = (TextView) itemView.findViewById(R.id.textview_item_more_playlist_title);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_item_more_playmusic);
        }
    }
}
