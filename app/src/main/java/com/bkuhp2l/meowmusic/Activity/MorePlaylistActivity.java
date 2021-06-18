package com.bkuhp2l.meowmusic.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bkuhp2l.meowmusic.Adapter.MorePlaylistAdapter;
import com.bkuhp2l.meowmusic.Model.Playlist;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.APIService;
import com.bkuhp2l.meowmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MorePlaylistActivity extends AppCompatActivity {
    ActionBar actionBar;
    LinearLayout btn_back;
    RecyclerView recyclerView;
    MorePlaylistAdapter morePlaylistAdapter;
    ArrayList<Playlist> arrayPlaylist;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_playlist);
        actionBar = getSupportActionBar();
        actionBar.hide();
        context = getApplicationContext();
        Mapping();
        SetData();
        EventClick();
    }

    private void SetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.getDataMorePlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                arrayPlaylist = (ArrayList<Playlist>) response.body();
                morePlaylistAdapter = new MorePlaylistAdapter(context, arrayPlaylist);
                recyclerView.setAdapter(morePlaylistAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                recyclerView.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void Mapping() {
        btn_back = (LinearLayout) findViewById(R.id.layout_more_playlist_title);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_more_playlist);
    }

    private void EventClick() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}