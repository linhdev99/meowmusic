package com.bkuhp2l.meowmusic.Fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bkuhp2l.meowmusic.Service.NotificationPlayMusic;
import com.bkuhp2l.meowmusic.Adapter.TopicAdapter;
import com.bkuhp2l.meowmusic.Model.Topic;
import com.bkuhp2l.meowmusic.R;
import com.bkuhp2l.meowmusic.Service.APIService;
import com.bkuhp2l.meowmusic.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.getSystemService;

public class Fragment_Topic extends Fragment {
    View view;
    ImageView btnViewmore;
    ArrayList<Topic> arrayTopic;
    TopicAdapter topicAdapter;
    RecyclerView recyclerViewTC;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic, container, false);
        Mapping();
        GetData();
        ButtonHandler();
        return view;
    }

    private void ButtonHandler() {
        btnViewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Fragment_Topic.class);
                NotificationPlayMusic.showNotification(getActivity(), "Topic", "They are all topics", intent);
            }
        });
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Topic>> callback = dataService.getDataTopic();
        callback.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                arrayTopic = (ArrayList<Topic>) response.body();
                topicAdapter = new TopicAdapter(getActivity(), arrayTopic);
                recyclerViewTC.setAdapter(topicAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewTC.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {

            }
        });
    }

    private void Mapping() {
        recyclerViewTC = (RecyclerView) view.findViewById(R.id.recyclerViewTopic);
        btnViewmore = (ImageView) view.findViewById(R.id.home_button_viewmore_topic);
    }
}
