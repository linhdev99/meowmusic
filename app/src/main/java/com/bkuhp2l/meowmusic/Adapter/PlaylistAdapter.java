package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bkuhp2l.meowmusic.Model.Playlist;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder {
        TextView txtNamePlaylist;
        ImageView imgBackgroundPlaylist, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.line_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtNamePlaylist = convertView.findViewById(R.id.textViewNamePlaylist);
            viewHolder.imgBackgroundPlaylist = convertView.findViewById(R.id.imgBackgroundPlaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imgPlaylist);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getImage()).into(viewHolder.imgBackgroundPlaylist);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgPlaylist);
        viewHolder.txtNamePlaylist.setText(playlist.getName());
        return convertView;
    }
}
