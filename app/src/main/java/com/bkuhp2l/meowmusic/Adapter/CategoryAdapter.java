package com.bkuhp2l.meowmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Model.Category;
import com.bkuhp2l.meowmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<Category> mCategories;

    public CategoryAdapter(Context mContext, ArrayList<Category> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View topicView = inflater.inflate(R.layout.line_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(topicView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category topic = mCategories.get(position);
        Picasso.with(mContext).load(topic.getImage()).into(holder.imgBackground);
        holder.name.setText(topic.getName());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imgBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewNameCategory);
            imgBackground = itemView.findViewById(R.id.imgBackgroundCategory);
        }
    }
}
