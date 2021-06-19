package com.bkuhp2l.meowmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Activity.PlayMusicActivity;
import com.bkuhp2l.meowmusic.Adapter.SongPlaylistAdapter;
import com.bkuhp2l.meowmusic.Model.Song;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.APIService;
import com.bkuhp2l.meowmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

public class Fragment_PlayMusic_Playlist extends Fragment implements SongPlaylistAdapter.OnSongListener {
    View view;
    RecyclerView recyclerView;
    ArrayList<Song> arraySong;
    Song current_song;
    int current_id;
    SongPlaylistAdapter songPlaylistAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playmusic_playlist, container, false);
        Mapping();
        DataIntent();
        SetData();
        EventClick();
        return view;
    }

    private void EventClick() {

    }

    private void SetData() {
        if (arraySong.size() > 0) {
            songPlaylistAdapter = new SongPlaylistAdapter(getActivity(), arraySong, this);
            recyclerView.setAdapter(songPlaylistAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            SetBackgroundSongPlaylist(current_id);
        }
    }

    public void SetBackgroundSongPlaylist(int id) {
        current_id = id;
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arraySong.size(); i++) {
                    View viewTemp = recyclerView.getLayoutManager().findViewByPosition(i);
                    CardView cardView = (CardView) viewTemp.findViewById(R.id.btn_cardview_song_playlist);
                    if (i == current_id) {
                        cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.choose_song_play));
                    } else {
                        cardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.notchoose_song_play));
                    }
                }
            }
        });
    }

    private void DataIntent() {
        Intent intent = getActivity().getIntent();
        if (intent != null){
            if (intent.hasExtra("music_playlist_array")) {
                current_id = (int) intent.getSerializableExtra("music_playlist_id_current");
                arraySong = (ArrayList<Song>) intent.getSerializableExtra("music_playlist_array");
            }
        }
    }
    private void Mapping() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_playmusic_playlist);
    }
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onSongClick(int position) {
//        SetBackgroundSongPlaylist(position);
        ((PlayMusicActivity)getActivity()).PlayMusicById(position);
    }
}
