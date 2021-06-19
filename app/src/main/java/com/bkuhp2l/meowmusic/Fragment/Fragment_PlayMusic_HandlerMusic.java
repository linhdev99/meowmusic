package com.bkuhp2l.meowmusic.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bkuhp2l.meowmusic.Activity.PlayMusicActivity;
import com.bkuhp2l.meowmusic.R;

public class Fragment_PlayMusic_HandlerMusic extends Fragment {
    View view;
    ImageView btn_shuffle, btn_previous, btn_play, btn_next, btn_repeat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playmusic_handler_music, container, false);
        Mapping();
        EventClickButton();
        return view;
    }

    private void EventClickButton() {
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PlayMusicActivity)getActivity()).PlayClickButton();
                btn_play.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_play.setClickable(true);
                    }
                }, 500);
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PlayMusicActivity)getActivity()).NextClickButton();
                btn_next.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_next.setClickable(true);
                    }
                }, 2000);
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PlayMusicActivity)getActivity()).PreviousClickButton();
                btn_previous.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_previous.setClickable(true);
                    }
                }, 2000);
            }
        });
        btn_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PlayMusicActivity)getActivity()).ShuffleClickButton();
                btn_shuffle.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_shuffle.setClickable(true);
                    }
                }, 500);
            }
        });
        btn_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PlayMusicActivity)getActivity()).RepeatClickButton();
                btn_repeat.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_repeat.setClickable(true);
                    }
                }, 500);
            }
        });
    }

    private void Mapping() {
        btn_shuffle = (ImageView) view.findViewById(R.id.btn_playmusic_handler_shuffle);
        btn_previous = (ImageView) view.findViewById(R.id.btn_playmusic_handler_previous);
        btn_play = (ImageView) view.findViewById(R.id.btn_playmusic_handler_play);
        btn_next = (ImageView) view.findViewById(R.id.btn_playmusic_handler_next);
        btn_repeat = (ImageView) view.findViewById(R.id.btn_playmusic_handler_repeat);
    }
}
