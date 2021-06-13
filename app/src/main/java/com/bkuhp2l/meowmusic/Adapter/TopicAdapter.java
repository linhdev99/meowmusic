package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Model.Topic;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<Topic> mTopics;

    public TopicAdapter(Context mContext, ArrayList<Topic> mTopics) {
        this.mContext = mContext;
        this.mTopics = mTopics;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View topicView = inflater.inflate(R.layout.line_topic, parent, false);
        ViewHolder viewHolder = new ViewHolder(topicView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.ViewHolder holder, int position) {
        Topic topic = mTopics.get(position);
        Picasso.with(mContext).load(topic.getImage()).into(holder.imgBackground);
        holder.name.setText(topic.getName());
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imgBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewNameTopic);
            imgBackground = itemView.findViewById(R.id.imgBackgroundTopic);
        }
    }
}
